package com.blockotlin.features.authentication.routes.signInUser

import com.blockotlin.features.authentication.data.AuthenticationData
import com.blockotlin.features.authentication.model.LoginRequestDto
import com.blockotlin.features.authentication.model.UserInfoDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.signInUser() {
    val authenticationData: AuthenticationData by inject(AuthenticationData::class.java)
    post("/public-api/v1/authentication/signup") {
        val request = call.receive<UserInfoDto>()
        val token = authenticationData.signIn(request)
        call.response.cookies.append(
            Cookie(
                name = "token",
                value = token,
                httpOnly = true,
                path = "/",
                secure = true,
                maxAge = 3600,
                sameSite = SameSite.Lax
            )
        )

        call.respond(mapOf("token" to token))
    }
}