package com.blockotlin.features.authentication.dao.mapper

import com.blockotlin.features.authentication.dao.entity.User
import com.blockotlin.features.authentication.model.UserInfoDto
import org.jetbrains.exposed.sql.ResultRow

class AuthenticationMapperImpl : AuthenticationMapper {

    override fun fromUserDaoToUserInfo(resultRow: ResultRow) = UserInfoDto(
        email = resultRow[User.email],
        name = resultRow[User.name],
        surname = resultRow[User.surname],
        birthDate = resultRow[User.birthDate],
        role = resultRow[User.role].toString(),
    )

}