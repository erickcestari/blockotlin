package com.blockotlin.jwt

import com.auth0.jwt.JWTVerifier
import com.blockotlin.features.authentication.model.UserInfoDto
import java.util.*

interface JwtManager {
    fun generateToken(userInfoDto: UserInfoDto): String
    fun getExpiration(): Date
    fun getVerifier(): JWTVerifier
    fun getUsernameFromToken(token: String?): String?
}