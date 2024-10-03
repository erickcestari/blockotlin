package com.blockotlin.features.product.data

import com.blockotlin.features.product.dao.ProductDao
import com.blockotlin.features.product.model.CreateProductDto


class ProductDataImpl(private val productDao: ProductDao) :
    ProductData {
    override fun createProduct(request: CreateProductDto): Long {
        return productDao.createProduct(request)
    }
}