package com.blockotlin.features.healthcheck.di

import com.blockotlin.features.healthcheck.data.HealthCheckDataImpl
import com.blockotlin.features.healthcheck.data.HealthCheckData
import org.koin.dsl.bind
import org.koin.dsl.module

val healthCheckModule = module {
    single { HealthCheckDataImpl() } bind HealthCheckData::class
}