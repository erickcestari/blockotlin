package com.blockotlin.features.product.dao

import com.blockotlin.extensions.connectToExampleDatabase
import com.blockotlin.features.product.dao.entity.Product
import com.blockotlin.features.product.dao.mapper.ProductMapper
import com.blockotlin.features.product.model.CreateProductDto
import com.blockotlin.features.product.model.FilterProductsDto
import com.blockotlin.features.product.model.ProductInfoDto
import com.blockotlin.features.product.model.UpdateProductDto
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ProductDaoImpl(private val mapper: ProductMapper) : ProductDao {
    override fun createProduct(request: CreateProductDto): Long {
        Database.connectToExampleDatabase()

        return transaction {
            addLogger(StdOutSqlLogger)
            val generatedId = Product.insert {
                it[name] = request.name
                it[description] = request.description
                it[price] = request.price
                it[image] = request.image
            } get Product.id

            generatedId
        }
    }

    override fun deleteProduct(id: Long): Boolean {
        Database.connectToExampleDatabase()

        return transaction {
            addLogger(StdOutSqlLogger)
            val rowsUpdated = Product.update({ Product.id eq id }) {
                it[isDeleted] = true
            }

            rowsUpdated > 0
        }
    }

    override fun listProduct(request: FilterProductsDto): List<ProductInfoDto> {
        Database.connectToExampleDatabase()

        return transaction {
            addLogger(StdOutSqlLogger)
            val query = Product.selectAll().andWhere { Product.isDeleted eq false }

            if (request.name != null) {
                query.andWhere { Product.name like "%${request.name}%" }
            }

            query.map { row ->
                mapper.fromProductDaoToProductInfo(row)
            }
        }
    }

    override fun updateProduct(id: Long, request: UpdateProductDto): Boolean {
        Database.connectToExampleDatabase()

        return transaction {
            addLogger(StdOutSqlLogger)
            val rowsUpdated = Product.update({ Product.id eq id }) {
                it[name] = request.name
                it[description] = request.description
                it[price] = request.price
                it[image] = request.image
            }

            rowsUpdated > 0
        }
    }
}