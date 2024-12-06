package com.blockotlin.features.authentication.routes.loginUser

import com.blockotlin.features.authentication.data.AuthenticationData
import com.blockotlin.features.authentication.model.LoginRequestDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.loginUser() {
    val authenticationData: AuthenticationData by inject(AuthenticationData::class.java)
    post("/public-api/v1/authentication/login") {
        val request = call.receive<LoginRequestDto>()
        val token = authenticationData.login(request)

        val isSecureConnection = call.request.origin.scheme == "https"

        call.response.cookies.append(
            Cookie(
                name = "token",
                value = token,
                httpOnly = true,
                secure = isSecureConnection,
                domain= ".viniciuscestari.dev",
                maxAge = 3600,
                path = "/",
                extensions = mapOf("SameSite" to "Lax")
            )
        )

        call.respond(mapOf("token" to token))
    }
}