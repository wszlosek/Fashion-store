package com.shop.routes

import com.shop.models.Category
import com.shop.models.toCategory
import com.shop.tables.CategoryTable
import com.shop.tables.ColorTable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.categorySerialization() {
    getCategory()
    postCategory()
    putCategory()
    deleteCategory()
}

private fun Application.getCategory() {
    routing {
        get("/category") {
            var categories = mutableListOf<Category>()
            transaction {
                categories = CategoryTable.selectAll().map { it.toCategory() }.toMutableList()
            }
            call.respond(categories)
        }

        get("/category/{id}") {
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
        post("/category") {
            val category = call.receive<Category>()
            addCategoryToDatabase(category)
            call.respondText("Category stored correctly", status = HttpStatusCode.Created)
        }
    }
}

private fun Application.putCategory() {
    routing {
        put("/category/{id}") {
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
        delete("/category") {
            transaction {
                SchemaUtils.drop(CategoryTable)
                SchemaUtils.create(CategoryTable)
            }
        }

        delete("/category/{id}") {
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