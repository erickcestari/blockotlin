package com.blockotlin.features.authentication.data

import com.blockotlin.features.authentication.dao.AuthenticationDao
import com.blockotlin.features.authentication.model.LoginRequestDto
import com.blockotlin.features.authentication.model.UserInfoDto
import com.blockotlin.jwt.JwtManager

class AuthenticationDataImpl(private val authenticationDao: AuthenticationDao, private val jwtManager: JwtManager) :
    AuthenticationData {
    override fun login(request: LoginRequestDto): String {
        val userInfo = authenticationDao.login(request)
        if (userInfo != null) {
            return jwtManager.generateToken(userInfo)
        } else {
            throw Exception("There is no such user")
        }
    }

    override fun getUserInfo(email: String): UserInfoDto {
        return authenticationDao.getUserInfo(email).apply { password = null }
    }

    override fun createUser(userInfoDto: UserInfoDto) {
        return authenticationDao.createUser(userInfoDto)
    }
}