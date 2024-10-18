package com.blockotlin.features.product.model

import java.math.BigDecimal

data class UpdateProductDto(
    val name: String,
    val price: BigDecimal,
    val image: String?,
    val description: String,
)