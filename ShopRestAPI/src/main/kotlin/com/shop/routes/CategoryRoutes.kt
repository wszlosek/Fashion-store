package com.shop.routes

import com.shop.models.*
import com.shop.tables.CategoryTable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.categorySerialization() {
    getCategory()
    postCategory()
    putCategory()
    deleteCategory()
}

private fun Application.getCategory() {
    routing {
        get(categorySign) {
            var categories = mutableListOf<Category>()
            transaction {
                categories = CategoryTable.selectAll().map { it.toCategory() }.toMutableList()
            }
            call.respond(categories)
        }

        get(categoryIdSign) {
            val id: Int = call.parameters["id"]!!.toInt()
            var category = Category()
            transaction {
                category = CategoryTable.select { CategoryTable.id eq id }.map { it.toCategory() }.first()
            }
            call.respond(category)
        }
    }
}

private fun Application.postCategory() {
    routing {
        post(categorySign) {
            val category = call.receive<Category>()
            addCategoryToDatabase(category)
            call.respondText("Category stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putCategory() {
    routing {
        put(categoryIdSign) {
            val id = call.parameters["id"]
            val category = call.receive<Category>()
            transaction {
                CategoryTable.update({ CategoryTable.id eq id!!.toInt() }) {
                    with(SqlExpressionBuilder) {
                        it[name] = category.name
                    }
                }
            }
        }
    }
}

private fun Application.deleteCategory() {
    routing {
        delete(categorySign) {
            transaction {
                SchemaUtils.drop(CategoryTable)
                SchemaUtils.create(CategoryTable)
            }
        }

        delete(categoryIdSign) {
            val id = call.parameters["id"]
            transaction {
                CategoryTable.deleteWhere { CategoryTable.id eq id!!.toInt() }
            }
        }
    }
}

private fun addCategoryToDatabase(category: Category) {
    transaction {
        CategoryTable.insert {
            it[name] = category.name
        }
    }
}