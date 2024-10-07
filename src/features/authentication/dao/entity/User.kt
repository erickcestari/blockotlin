package com.blockotlin.features.authentication.dao.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object User : Table("user") {
    val id = long("id").autoIncrement().uniqueIndex()
    val firstName = varchar("FirstName", 50)
    val lastName = varchar("LastName", 50)
    val birthDate = date("birth_date")
    val email = varchar("Email", 50)
    val password = varchar("Password",400)
    val role = enumeration("role", Role::class)

    override val primaryKey = PrimaryKey(id, email)
}