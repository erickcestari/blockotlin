package com.blockotlin.features.product.dao

import com.blockotlin.features.product.model.CreateProductDto

interface ProductDao {
    fun createProduct(request: CreateProductDto)
}