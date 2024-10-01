package com.blockotlin.features.starwars.di

import com.blockotlin.features.starwars.data.StarWarsData
import com.blockotlin.features.starwars.data.StarWarsDataImpl
import com.blockotlin.features.starwars.remote.StarWarsRemote
import com.blockotlin.features.starwars.remote.StarWarsRemoteImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val starWarsModule = module {
    single { StarWarsRemoteImpl(get()) } bind StarWarsRemote::class
    single { StarWarsDataImpl(get()) } bind StarWarsData::class
}