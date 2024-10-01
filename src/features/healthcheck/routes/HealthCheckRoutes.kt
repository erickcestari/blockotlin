package com.blockotlin.features.healthcheck.routes

import com.blockotlin.features.healthcheck.routes.gethealthcheck.getHealthCheck
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.healthCheckRoutes() {
    routing {
        getHealthCheck()
    }
}