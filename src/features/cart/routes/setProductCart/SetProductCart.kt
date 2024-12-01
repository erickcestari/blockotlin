package com.blockotlin.features.cart.routes.setProductCart

import com.blockotlin.extensions.getTokenFromAuthorization
import com.blockotlin.extensions.getTokenFromCookie
import com.blockotlin.features.cart.data.CartData
import com.blockotlin.features.cart.model.SetProductCardDto
import com.blockotlin.jwt.JwtManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.setProductCart() {
    val cartData: CartData by inject(CartData::class.java)

    put("/api/v1/cart/user") {
        var token = call.getTokenFromCookie()
        if(token == null) {
            token = call.getTokenFromAuthorization()!!
        }

        val jwtManager: JwtManager by inject(JwtManager::class.java)
        val userId = jwtManager.getVerifier().verify(token).getClaim("userId").asLong()

        val body = call.receive<SetProductCardDto>()
        body.userId = userId;

        cartData.setProductCart(body)

        call.respond(HttpStatusCode.OK)
    }
}

