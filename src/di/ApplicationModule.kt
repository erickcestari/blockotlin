package com.blockotlin.di

import com.blockotlin.httpclient.HttpService
import com.blockotlin.httpclient.HttpServiceImpl
import com.blockotlin.jwt.JwtManager
import com.blockotlin.jwt.JwtManagerImpl
import com.typesafe.config.ConfigFactory
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val applicationModule = module {
    single { JwtManagerImpl(get(qualifier = named("jwtSecretProperty"))) } bind JwtManager::class
    single<String>(named("jwtSecretProperty")) { ConfigFactory.load().getString("jwt.secret").toString() }
    factory { HttpServiceImpl() } bind HttpService::class
}