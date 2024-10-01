package com.blockotlin.features.starwars.remote

import com.blockotlin.features.starwars.model.Movie

interface StarWarsRemote {
    suspend fun getMovie(): Movie
}