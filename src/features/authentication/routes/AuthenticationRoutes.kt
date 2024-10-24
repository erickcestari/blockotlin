package com.blockotlin.features.authentication.routes

import com.blockotlin.features.authentication.routes.createUser.createUser
import com.blockotlin.features.authentication.routes.loginUser.loginUser
import com.blockotlin.features.authentication.routes.signInUser.signInUser
import com.blockotlin.features.authentication.routes.userInfo.userInfo
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.authenticationRoutes() {
    routing {
        loginUser()
        signInUser()
        authenticate("auth-admin") {
            createUser()
        }
        authenticate("auth-jwt") {
            userInfo()
        }
    }
}