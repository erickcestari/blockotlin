package com.blockotlin.features.starwars.data

import com.blockotlin.features.starwars.model.Movie
import com.blockotlin.features.starwars.remote.StarWarsRemote

class StarWarsDataImpl(private val remote: StarWarsRemote) : StarWarsData {
    override suspend fun getMovie(): Movie {
        return remote.getMovie()
    }
}