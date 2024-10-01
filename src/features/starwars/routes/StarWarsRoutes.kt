package com.blockotlin.features.starwars.routes

import com.blockotlin.features.starwars.routes.getmovie.getMovie
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.starWarsRoutes() {
    routing {
        authenticate {
            getMovie()
        }
    }
}