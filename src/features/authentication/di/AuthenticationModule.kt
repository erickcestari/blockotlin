package com.blockotlin.features.authentication.di

import com.blockotlin.features.authentication.dao.AuthenticationDao
import com.blockotlin.features.authentication.dao.AuthenticationDaoImpl
import com.blockotlin.features.authentication.dao.mapper.AuthenticationMapper
import com.blockotlin.features.authentication.dao.mapper.AuthenticationMapperImpl
import com.blockotlin.features.authentication.data.AuthenticationData
import com.blockotlin.features.authentication.data.AuthenticationDataImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val authenticationModule = module {
    single { AuthenticationDaoImpl(get()) } bind AuthenticationDao::class
    single { AuthenticationDataImpl(get(), get()) } bind AuthenticationData::class
    factory { AuthenticationMapperImpl() } bind AuthenticationMapper::class
}