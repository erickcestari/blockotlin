package com.blockotlin.features.crypto.dao.mapper

import com.blockotlin.features.authentication.model.UserInfoDto
import org.jetbrains.exposed.sql.ResultRow

interface CryptoMapper {
    fun fromUserDaoToUserInfo(resultRow: ResultRow): UserInfoDto
}