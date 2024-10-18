package com.blockotlin.features.product.model

import java.math.BigDecimal

data class ProductInfoDto(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val image: String?,
    val description: String,
)