package com.blockotlin

import com.blockotlin.features.authentication.routes.authenticationRoutes
import com.blockotlin.features.healthcheck.routes.healthCheckRoutes
import com.blockotlin.features.product.routes.productRoutes
import com.blockotlin.features.starwars.routes.starWarsRoutes
import io.ktor.server.application.*

fun Application.routes() {
    authenticationRoutes()
    healthCheckRoutes()
    starWarsRoutes()
    productRoutes()
}