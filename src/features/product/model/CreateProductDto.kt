package com.blockotlin.features.product.model

import java.math.BigDecimal

data class CreateProductDto(
    val name: String,
    val price: BigDecimal,
    val image: String?,
    val description: String,
)