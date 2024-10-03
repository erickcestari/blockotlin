package com.blockotlin.features.product.model

data class CreateProductDto(
    val name: String,
    val price: Long,
    val category: String,
    val image: String?,
    val description: String,
)