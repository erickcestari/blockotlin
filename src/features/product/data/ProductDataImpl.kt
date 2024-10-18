package com.blockotlin.features.product.data

import com.blockotlin.features.product.dao.ProductDao
import com.blockotlin.features.product.model.CreateProductDto
import com.blockotlin.features.product.model.FilterProductsDto
import com.blockotlin.features.product.model.ProductInfoDto
import com.blockotlin.features.product.model.UpdateProductDto


class ProductDataImpl(private val productDao: ProductDao) :
    ProductData {
    override fun createProduct(request: CreateProductDto): Long {
        return productDao.createProduct(request)
    }

    override fun listProduct(request: FilterProductsDto): List<ProductInfoDto> {
        return productDao.listProduct(request)
    }

    override fun updateProduct(id: Long, request: UpdateProductDto): Boolean {
        return productDao.updateProduct(id, request)
    }

    override fun deleteProduct(id: Long): Boolean {
        return productDao.deleteProduct(id)
    }
}