package com.blockotlin.features.product.dao

import com.blockotlin.features.product.model.CreateProductDto
import com.blockotlin.features.product.model.FilterProductsDto
import com.blockotlin.features.product.model.ProductInfoDto
import com.blockotlin.features.product.model.UpdateProductDto

interface ProductDao {
    fun createProduct(request: CreateProductDto): Long
    fun listProduct(request: FilterProductsDto): List<ProductInfoDto>
    fun updateProduct(id: Long, request: UpdateProductDto): Boolean
    fun deleteProduct(id: Long): Boolean
}