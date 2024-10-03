package com.blockotlin.features.product.routes

import com.blockotlin.features.product.routes.createProduct.createProduct
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.productRoutes() {
    routing {
        authenticate("auth-admin") {
            createProduct()
        }
    }
}