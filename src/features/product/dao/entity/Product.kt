package com.blockotlin.features.product.dao.entity

import org.jetbrains.exposed.sql.Table

object Product : Table("product") {
    val id = long("id").autoIncrement().uniqueIndex()
    val name = varchar("name", 255)
    val price = decimal("price", 9,2)
    val description = varchar("description", 5555)
    val image = text("image").nullable()
    val isDeleted = bool("isDeleted").default(false)

    override val primaryKey = PrimaryKey(id)
}