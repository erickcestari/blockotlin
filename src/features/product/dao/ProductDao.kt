package com.blockotlin.features.product.dao

import com.blockotlin.features.product.model.CreateProductDto
import com.blockotlin.features.product.model.FilterProductsDto
import com.blockotlin.features.product.model.ProductInfoDto

interface ProductDao {
    fun createProduct(request: CreateProductDto): Long
    fun listProduct(request: FilterProductsDto): List<ProductInfoDto>
}