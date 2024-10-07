package com.blockotlin.features.authentication.data

import com.blockotlin.features.authentication.model.LoginRequestDto
import com.blockotlin.features.authentication.model.UserInfoDto

interface AuthenticationData {
    fun login(request: LoginRequestDto): String
    fun getUserInfo(email:String): UserInfoDto
    fun createUser(userInfoDto: UserInfoDto)
    fun signIn(userInfoDto: UserInfoDto): String
}