package com.blockotlin.features.starwars.data

import com.blockotlin.features.starwars.model.Movie

interface StarWarsData {
    suspend fun getMovie(): Movie
}