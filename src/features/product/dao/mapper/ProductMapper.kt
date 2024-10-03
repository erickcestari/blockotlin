package com.blockotlin.features.product.dao.mapper

import com.blockotlin.features.authentication.model.UserInfoDto
import com.blockotlin.features.product.model.ProductInfoDto
import org.jetbrains.exposed.sql.ResultRow

interface ProductMapper {
    fun fromProductDaoToProductInfo(resultRow: ResultRow): ProductInfoDto
}