package com.blockotlin

import com.blockotlin.features.authentication.routes.authenticationRoutes
import com.blockotlin.features.crypto.routes.cryptoRoutes
import com.blockotlin.features.healthcheck.routes.healthCheckRoutes
import com.blockotlin.features.starwars.routes.starWarsRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.routes() {
    authenticationRoutes()
    healthCheckRoutes()
    starWarsRoutes()
    cryptoRoutes()
}