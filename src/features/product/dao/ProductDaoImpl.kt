package com.blockotlin.features.product.dao

import com.blockotlin.extensions.connectToExampleDatabase
import com.blockotlin.features.product.dao.entity.Product
import com.blockotlin.features.product.dao.mapper.ProductMapper
import com.blockotlin.features.product.model.CreateProductDto
import com.blockotlin.features.product.model.FilterProductsDto
import com.blockotlin.features.product.model.ProductInfoDto
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

    override fun listProduct(request: FilterProductsDto): List<ProductInfoDto> {
        Database.connectToExampleDatabase()

        return transaction {
            addLogger(StdOutSqlLogger)
            val query = Product.selectAll()

            if (request.name != null) {
                query.andWhere { Product.name like "%${request.name}%" }
            }

            query.map { row ->
                mapper.fromProductDaoToProductInfo(row)
            }
        }
    }
}