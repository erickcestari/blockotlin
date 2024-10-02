package com.blockotlin.features.authentication.dao

import com.blockotlin.features.authentication.model.LoginRequestDto
import com.blockotlin.features.authentication.model.UserInfoDto

interface AuthenticationDao {
    fun login(request: LoginRequestDto): UserInfoDto?
    fun getUserInfo(email:String): UserInfoDto
    fun createUser(userInfoDto: UserInfoDto)
}