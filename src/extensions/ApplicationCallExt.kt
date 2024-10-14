package com.blockotlin.extensions

import io.ktor.server.application.*

fun ApplicationCall.getTokenFromCookie(): String? {
    val cookieHeader = this.request.headers["Cookie"]
    val tokenPrefix = "token="

    // Extract token from the Cookie header
    return cookieHeader
        ?.split(";")
        ?.map { it.trim() }
        ?.firstOrNull { it.startsWith(tokenPrefix) }
        ?.substring(tokenPrefix.length)
}
