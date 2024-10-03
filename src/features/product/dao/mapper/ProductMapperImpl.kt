package com.blockotlin.features.product.dao.mapper

import com.blockotlin.features.authentication.dao.entity.User
import com.blockotlin.features.authentication.model.UserInfoDto
import com.blockotlin.features.product.dao.ProductDao
import com.blockotlin.features.product.dao.entity.Product
import com.blockotlin.features.product.model.ProductInfoDto
import org.jetbrains.exposed.sql.ResultRow

class ProductMapperImpl : ProductMapper {

    override fun fromProductDaoToProductInfo(resultRow: ResultRow) = ProductInfoDto(
        id = resultRow[Product.id],
        name = resultRow[Product.name],
        price = resultRow[Product.price],
        image = resultRow[Product.image],
        description = resultRow[Product.description],
    )

}