package com.blockotlin.utils

import com.blockotlin.extensions.connectToExampleDatabase
import com.blockotlin.features.authentication.dao.entity.User
import com.blockotlin.features.cart.dao.entity.Cart
import com.blockotlin.features.product.dao.entity.Product
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.StdOutSqlLogger

object DatabaseFactory {
    fun init() {
        Database.connectToExampleDatabase()

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(User, Product, Cart)
        }
    }
}
