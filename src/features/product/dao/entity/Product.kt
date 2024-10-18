package com.blockotlin.features.product.dao.entity

import org.jetbrains.exposed.sql.Table

object Product : Table("product") {
    val id = long("id").autoIncrement().uniqueIndex()
    val name = varchar("name", 255)
    val price = long("price")
    val description = varchar("description", 555)
    val image = varchar("image", 255).nullable()
    val isDeleted = bool("isDeleted").default(false)

    override val primaryKey = PrimaryKey(id)
}