package com.blockotlin.features.cart.dao.entity

import org.jetbrains.exposed.sql.Table
import com.blockotlin.features.product.dao.entity.Product
import com.blockotlin.features.authentication.dao.entity.User

object Cart : Table("cart") {
    val userId = reference("user_id", User.id)
    val productId = reference("product_id", Product.id)
    val quantity = integer("quantity").default(1)

    override val primaryKey = PrimaryKey(userId, productId)
}
