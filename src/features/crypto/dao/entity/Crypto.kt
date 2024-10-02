package com.blockotlin.features.authentication.dao.entity

import org.jetbrains.exposed.sql.Table

object Crypto : Table("crypto") {
    val id = long("id").autoIncrement().uniqueIndex()
    val name = varchar("name", 255)
    val symbol = varchar("symbol", 255)
    val price = long("price")
    val description = varchar("description", 555)


    override val primaryKey = PrimaryKey(id)
}