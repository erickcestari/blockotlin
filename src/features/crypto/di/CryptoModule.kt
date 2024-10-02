package com.blockotlin.features.crypto.di

import com.blockotlin.features.crypto.dao.CryptoDao
import com.blockotlin.features.crypto.dao.CryptoDaoImpl
import com.blockotlin.features.crypto.data.CryptoData
import com.blockotlin.features.crypto.data.CryptoDataImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val cryptoModule = module {
    single { CryptoDataImpl(get()) } bind CryptoData::class
    single { CryptoDaoImpl(get()) } bind CryptoDao::class
}