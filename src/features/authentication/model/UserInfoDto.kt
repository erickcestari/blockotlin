package com.blockotlin.features.authentication.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class UserInfoDto(
    val firstName: String,
    val lastName: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") val birthDate: LocalDate,
    val email: String,
    val id: Long? = null,
    var password: String? = null,
    var role: String? = null,
)