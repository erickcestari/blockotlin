package com.blockotlin.features.authentication.routes.signInUser

import com.blockotlin.features.authentication.data.AuthenticationData
import com.blockotlin.features.authentication.model.LoginRequestDto
import com.blockotlin.features.authentication.model.UserInfoDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.koin.java.KoinJavaComponent.inject

fun Route.signInUser() {
    val authenticationData: AuthenticationData by inject(AuthenticationData::class.java)
    post("/public-api/v1/authentication/signup") {
        val request = call.receive<UserInfoDto>()
        val cookieConfig = CookieConfiguration().apply {
            path = "/"
            secure = true
            httpOnly = true
            maxAgeInSeconds = 3600
            extensions["SameSite"] = "none"
        }

        val token = authenticationData.signIn(request)
        val cookie = Cookie(
            name = "token",
            value = token,
            httpOnly = true,
            path = "/",
            secure = true,
            maxAge = 3600,
            extensions = cookieConfig.extensions
        )

        call.response.cookies.append(cookie)

        call.respond(mapOf("token" to token))
    }
}