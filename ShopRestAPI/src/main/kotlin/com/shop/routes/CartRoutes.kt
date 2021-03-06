package com.shop.routes

import com.shop.models.*
import com.shop.tables.CartTable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.cartSerialization() {
    getCart()
    postCart()
    putCart()
    deleteCart()
}

private fun Application.getCart() {
    routing {
        get(cartSign) {
            var carts = mutableListOf<Cart>()
            transaction {
                carts = CartTable.selectAll().map { it.toCart() }.toMutableList()
            }
            call.respond(carts)
        }

        get(cartIdSign) {
            val id: Int = call.parameters["id"]!!.toInt()
            var cart = Cart()
            transaction {
                cart = CartTable.select { CartTable.id eq id }.map { it.toCart() }.first()
            }
            call.respond(cart)
        }
    }
}

private fun Application.postCart() {
    routing {
        post(cartSign) {
            val color = call.receive<Cart>()
            addCartToDatabase(color)
            call.respondText("Color stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putCart() {
    routing {
        put(cartIdSign) {
            val id = call.parameters["id"]
            val cart = call.receive<Cart>()
            transaction {
                CartTable.update({ CartTable.id eq id!!.toInt() }) {
                    with(SqlExpressionBuilder) {
                        it[CartTable.id] = cart.id
                        it[userId] = cart.userId
                        it[productId] = cart.productId
                        it[amount] = cart.amount
                    }
                }
            }
        }
    }
}

private fun Application.deleteCart() {
    routing {
        delete(cartSign) {
            transaction {
                SchemaUtils.drop(CartTable)
                SchemaUtils.create(CartTable)
            }
        }

        delete(cartIdSign) {
            val id = call.parameters["id"]
            transaction {
                CartTable.deleteWhere { CartTable.id eq id!!.toInt() }
            }
        }
    }
}

private fun addCartToDatabase(cart: Cart) {
    transaction {
        CartTable.insert {
            it[id] = cart.id
            it[userId] = cart.userId
            it[productId] = cart.productId
            it[amount] = cart.amount
        }
    }
}
