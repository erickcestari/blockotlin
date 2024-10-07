package com.blockotlin.features.authentication.dao.mapper

import com.blockotlin.features.authentication.dao.entity.User
import com.blockotlin.features.authentication.model.UserInfoDto
import org.jetbrains.exposed.sql.ResultRow

class AuthenticationMapperImpl : AuthenticationMapper {

    override fun fromUserDaoToUserInfo(resultRow: ResultRow) = UserInfoDto(
        id = resultRow[User.id],
        email = resultRow[User.email],
        firstName = resultRow[User.firstName],
        lastName = resultRow[User.lastName],
        birthDate = resultRow[User.birthDate],
        role = resultRow[User.role].toString(),
    )

}