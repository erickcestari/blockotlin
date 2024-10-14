package com.blockotlin.extensions

import io.ktor.server.application.*

fun ApplicationCall.getTokenFromCookie(): String? {
    val cookieHeader = this.request.headers["Cookie"]
    val tokenPrefix = "token="

    return cookieHeader
        ?.split(";")
        ?.map { it.trim() }
        ?.firstOrNull { it.startsWith(tokenPrefix) }
        ?.substring(tokenPrefix.length)
}

fun ApplicationCall.getTokenFromAuthorization(): String? {
    return this.request.headers["Authorization"]?.substring(7)
}

