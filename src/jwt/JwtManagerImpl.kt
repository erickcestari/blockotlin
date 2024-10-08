package com.blockotlin.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.blockotlin.features.authentication.model.UserInfoDto
import org.koin.core.component.KoinComponent
import java.util.*


class JwtManagerImpl(secret: String) : JwtManager, KoinComponent {
    private val validityInMs = 36_000_00 * 1
    private val algorithm = Algorithm.HMAC256(secret)

    override fun getVerifier(): JWTVerifier = JWT.require(algorithm).build()

    override fun generateToken(userInfoDto: UserInfoDto): String = JWT.create()
        .withSubject("Authentication")
        .withClaim("userId", userInfoDto.id)
        .withClaim("email", userInfoDto.email)
        .withClaim("role", userInfoDto.role)
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    override fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

    override fun getUsernameFromToken(token: String?): String? {
        return JWT.decode(token).getClaim("email").toString().replace("\"", "")
    }

}