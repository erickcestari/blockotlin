package com.blockotlin.features.product.dao

import com.blockotlin.extensions.connectToExampleDatabase
import com.blockotlin.features.product.dao.entity.Product
import com.blockotlin.features.product.model.CreateProductDto
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ProductDaoImpl() : ProductDao {
    override fun createProduct(request: CreateProductDto): Long {
        Database.connectToExampleDatabase()

        return transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Product)
            val generatedId = Product.insert {
                it[name] = request.name
                it[description] = request.description
                it[price] = request.price
                it[image] = request.image
            } get Product.id

            generatedId
        }
    }
}