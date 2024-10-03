package com.blockotlin.features.product.data

import com.blockotlin.features.product.model.CreateProductDto

interface ProductData {
    fun createProduct(request: CreateProductDto): Long
}