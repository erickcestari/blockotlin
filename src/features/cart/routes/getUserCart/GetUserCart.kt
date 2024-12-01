package com.blockotlin.features.cart.routes.getUserCart

import com.blockotlin.extensions.getTokenFromAuthorization
import com.blockotlin.extensions.getTokenFromCookie
import com.blockotlin.features.cart.data.CartData
import com.blockotlin.jwt.JwtManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.getUserCart() {
    val cartData: CartData by inject(CartData::class.java)

    get("/api/v1/cart/user") {
        var token = call.getTokenFromCookie()
        if(token == null) {
            token = call.getTokenFromAuthorization()!!
        }

        val jwtManager: JwtManager by inject(JwtManager::class.java)
        val userId = jwtManager.getVerifier().verify(token).getClaim("userId").asLong()

        val products = cartData.getUserCart(userId)
        call.respond(HttpStatusCode.OK, mapOf("products" to products))
    }
}

