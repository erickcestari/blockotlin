package com.blockotlin.features.authentication.routes.signInUser

import com.blockotlin.features.authentication.data.AuthenticationData
import com.blockotlin.features.authentication.model.LoginRequestDto
import com.blockotlin.features.authentication.model.UserInfoDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*
import org.koin.ktor.ext.inject

fun Route.signInUser() {
    val authenticationData: AuthenticationData by inject()

    post("/public-api/v1/authentication/signup") {
        val request = call.receive<UserInfoDto>()
        val token = authenticationData.signIn(request)

        call.response.cookies.append(
            Cookie(
                name = "token",
                value = token,
                httpOnly = true,
                secure = true,
                maxAge = 3600,
                path = "/",
                extensions = mapOf("SameSite" to "None")
            )
        )

        call.respond(HttpStatusCode.OK, mapOf("token" to token))
    }
}
