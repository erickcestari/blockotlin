package com.blockotlin.features.authentication.data

import com.blockotlin.features.authentication.dao.AuthenticationDao
import com.blockotlin.features.authentication.dao.entity.Role
import com.blockotlin.features.authentication.model.LoginRequestDto
import com.blockotlin.features.authentication.model.UserInfoDto
import com.blockotlin.jwt.JwtManager
import java.security.InvalidParameterException

class AuthenticationDataImpl(private val authenticationDao: AuthenticationDao, private val jwtManager: JwtManager) :
    AuthenticationData {
    override fun login(request: LoginRequestDto): String {
        val userInfo = authenticationDao.login(request)
        if (userInfo != null) {
            return jwtManager.generateToken(userInfo)
        } else {
            throw InvalidParameterException("There is no such user")
        }
    }

    override fun getUserInfo(email: String): UserInfoDto {
        return authenticationDao.getUserInfo(email).apply { password = null }
    }

    override fun createUser(userInfoDto: UserInfoDto) {
        if (userInfoDto.password == null) {
            throw InvalidParameterException("Password is required")
        }

        if (userInfoDto.password!!.length < 6) {
            throw InvalidParameterException("Password is too short")
        }
        return authenticationDao.createUser(userInfoDto)
    }

    override fun signIn(userInfoDto: UserInfoDto): String {
        if (userInfoDto.password == null) {
            throw InvalidParameterException("Password is required")
        }

        if (userInfoDto.password!!.length < 6) {
            throw InvalidParameterException("Password is too short")
        }

        userInfoDto.role = Role.CLIENT.toString()

        authenticationDao.createUser(userInfoDto)
        val userInfo = authenticationDao.getUserInfo(userInfoDto.email)
        return jwtManager.generateToken(userInfo)
    }
}