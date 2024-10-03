package com.blockotlin.features.product.dao

import com.blockotlin.features.authentication.dao.mapper.AuthenticationMapper
import com.blockotlin.features.product.model.CreateProductDto

class ProductDaoImpl(private val mapper: AuthenticationMapper) : ProductDao {
    override fun createProduct(request: CreateProductDto) {
        TODO("Not yet implemented")
    }
}