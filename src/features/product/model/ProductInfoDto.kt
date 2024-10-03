package com.blockotlin.features.product.model

data class ProductInfoDto(
    val id: Long,
    val name: String,
    val price: Long,
    val image: String?,
    val description: String,
)