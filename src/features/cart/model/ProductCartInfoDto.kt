package com.blockotlin.features.cart.model

import java.math.BigDecimal

data class ProductCartInfoDto(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val image: String?,
    val description: String,
    val quantity: Int,
)