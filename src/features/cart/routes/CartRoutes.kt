package com.blockotlin.features.cart.routes

import com.blockotlin.features.cart.routes.getUserCart.getUserCart
import com.blockotlin.features.cart.routes.setProductCart.setProductCart
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.cartRoutes() {
    routing {
        authenticate("auth-jwt") {
            setProductCart()
            getUserCart()
        }
    }
}