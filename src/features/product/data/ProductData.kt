package com.blockotlin.features.product.data

import com.blockotlin.features.product.dao.entity.Product
import com.blockotlin.features.product.model.CreateProductDto
import com.blockotlin.features.product.model.FilterProductsDto
import com.blockotlin.features.product.model.ProductInfoDto

interface ProductData {
    fun createProduct(request: CreateProductDto): Long
    fun listProduct(request: FilterProductsDto): List<ProductInfoDto>
}