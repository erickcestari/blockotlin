package com.blockotlin.features.crypto.routes

import com.blockotlin.features.authentication.routes.createUser.createUser
import com.blockotlin.features.authentication.routes.loginUser.loginUser
import com.blockotlin.features.authentication.routes.userInfo.userInfo
import com.blockotlin.features.crypto.routes.addCrypto.addCrypto
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.cryptoRoutes() {
    routing {
        authenticate("auth-admin") {
            addCrypto()
        }
    }
}