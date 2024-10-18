package com.blockotlin.features.product.model

data class UpdateProductDto(
    val name: String,
    val price: Long,
    val image: String?,
    val description: String,
)