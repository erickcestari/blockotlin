package com.blockotlin.features.authentication.dao.mapper

import com.blockotlin.features.authentication.model.UserInfoDto
import org.jetbrains.exposed.sql.ResultRow

interface AuthenticationMapper {
    fun fromUserDaoToUserInfo(resultRow: ResultRow): UserInfoDto
}