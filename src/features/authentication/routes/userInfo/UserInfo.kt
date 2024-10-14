package com.blockotlin.features.authentication.routes.userInfo

import com.blockotlin.extensions.getTokenFromAuthorization
import com.blockotlin.extensions.getTokenFromCookie
import com.blockotlin.features.authentication.data.AuthenticationData
import com.blockotlin.jwt.JwtManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.userInfo() {
    val authenticationData: AuthenticationData by inject(AuthenticationData::class.java)
    val jwtManager: JwtManager by inject(JwtManager::class.java)
    get("/api/v1/authentication/user-info") {
        var token = call.getTokenFromCookie()
        if(token == null) {
            token = call.getTokenFromAuthorization()!!
        }
        call.respond(authenticationData.getUserInfo(jwtManager.getUsernameFromToken(token)!!))
    }
}