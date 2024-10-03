package com.blockotlin.features.product.data

import com.blockotlin.features.product.dao.ProductDao
import com.blockotlin.features.product.model.CreateProductDto
import com.blockotlin.features.product.model.FilterProductsDto
import com.blockotlin.features.product.model.ProductInfoDto


class ProductDataImpl(private val productDao: ProductDao) :
    ProductData {
    override fun createProduct(request: CreateProductDto): Long {
        return productDao.createProduct(request)
    }

    override fun listProduct(request: FilterProductsDto): List<ProductInfoDto> {
        return productDao.listProduct(request)
    }
}