package com.blockotlin.features.product.di

import com.blockotlin.features.product.dao.ProductDao
import com.blockotlin.features.product.dao.ProductDaoImpl
import com.blockotlin.features.product.dao.mapper.ProductMapper
import com.blockotlin.features.product.dao.mapper.ProductMapperImpl
import com.blockotlin.features.product.data.ProductData
import com.blockotlin.features.product.data.ProductDataImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val productModule = module {
    single { ProductDataImpl(get()) } bind ProductData::class
    single { ProductDaoImpl(get()) } bind ProductDao::class
    factory { ProductMapperImpl() } bind ProductMapper::class
}