package com.blockotlin.features.cart.di

import com.blockotlin.features.cart.dao.CartDao
import com.blockotlin.features.cart.dao.CartDaoImpl
import com.blockotlin.features.cart.data.CartData
import com.blockotlin.features.cart.data.CartDataImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val cartModule = module {
    single { CartDataImpl(get()) } bind CartData::class
    single { CartDaoImpl() } bind CartDao::class
}