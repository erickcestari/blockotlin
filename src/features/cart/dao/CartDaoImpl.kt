package com.blockotlin.features.cart.dao

import com.blockotlin.extensions.connectToExampleDatabase
import com.blockotlin.features.cart.dao.entity.Cart
import com.blockotlin.features.cart.model.ProductCartInfoDto
import com.blockotlin.features.cart.model.SetProductCardDto
import com.blockotlin.features.product.dao.entity.Product
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class CartDaoImpl : CartDao {

    override fun setProductCart(request: SetProductCardDto) {
        Database.connectToExampleDatabase()

        transaction {
            addLogger(StdOutSqlLogger)

            if (request.quantity == 0) {
                Cart.deleteWhere {
                    (Cart.userId eq  request.userId) and
                            (Cart.productId eq request.productId)
                }
                return@transaction Unit
            }

            val existingItem = Cart.select {
                (Cart.userId eq request.userId) and
                        (Cart.productId eq request.productId)
            }.singleOrNull()

            if (existingItem != null) {
                Cart.update({
                    (Cart.userId eq request.userId) and
                            (Cart.productId eq request.productId)
                }) {
                    it[quantity] = request.quantity
                }
                return@transaction Unit
            }

            Cart.insert {
                it[userId] = request.userId
                it[productId] = request.productId
                it[quantity] = request.quantity
            }
        }
    }

    override fun getUserCart(userId: Long): List<ProductCartInfoDto> {
        Database.connectToExampleDatabase()

        return transaction {
            addLogger(StdOutSqlLogger)

            (Cart innerJoin Product)
                .select { Cart.userId eq userId }
                .map { row ->
                    ProductCartInfoDto(
                        id = row[Product.id],
                        name = row[Product.name],
                        description = row[Product.description],
                        price = row[Product.price],
                        quantity = row[Cart.quantity],
                        image = row[Product.image]
                    )
                }
        }
    }
}
