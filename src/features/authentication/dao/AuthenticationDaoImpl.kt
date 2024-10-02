package com.blockotlin.features.authentication.dao

import com.blockotlin.extensions.connectToExampleDatabase
import com.blockotlin.features.authentication.dao.entity.Role
import com.blockotlin.features.authentication.dao.entity.User
import com.blockotlin.features.authentication.dao.mapper.AuthenticationMapper
import com.blockotlin.features.authentication.model.LoginRequestDto
import com.blockotlin.features.authentication.model.UserInfoDto
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt

class AuthenticationDaoImpl(private val mapper: AuthenticationMapper) : AuthenticationDao {

    override fun login(request: LoginRequestDto): UserInfoDto? {
        Database.connectToExampleDatabase()

        val userDao = transaction {
            addLogger(StdOutSqlLogger)
            return@transaction User.select { User.email eq request.email }.firstOrNull()
        }

        if (userDao == null) {return null}

        val userInfo = mapper.fromUserDaoToUserInfo(userDao)
        val storedPasswordHash = userDao[User.password]

        return if (validatePassword(request.password, storedPasswordHash)) {
            return userInfo
        } else {
            null
        }
    }

    private fun validatePassword(inputPassword: String, storedPasswordHash: String): Boolean {
        return BCrypt.checkpw(inputPassword, storedPasswordHash)
    }

    override fun getUserInfo(email:String): UserInfoDto {
        Database.connectToExampleDatabase()

        val userInfo = transaction {
            addLogger(StdOutSqlLogger)
            return@transaction mapper.fromUserDaoToUserInfo(User.select { User.email eq email }.single())
        }
        return userInfo
    }

    override fun createUser(userInfoDto: UserInfoDto) {
        Database.connectToExampleDatabase()

        val userEmailExists = transaction {
            addLogger(StdOutSqlLogger)
            return@transaction User.select { User.email eq userInfoDto.email }.count()
        } > 0

        if (userEmailExists) {throw Exception("Something went wrong")}

        val passwordHash = BCrypt.hashpw(userInfoDto.password, BCrypt.gensalt())

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(User)
            User.insert {
                it[name] = userInfoDto.name
                it[surname] = userInfoDto.surname
                it[email] = userInfoDto.email
                it[birthDate] = userInfoDto.birthDate
                it[password] = passwordHash
                it[role] = Role.CLIENT
            }
        }
    }
}