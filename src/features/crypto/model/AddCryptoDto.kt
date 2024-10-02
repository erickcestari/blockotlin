package com.blockotlin.features.crypto.model

data class AddCryptoDto(
    val name: String,
    val symbol: String?,
    val price: Long,
    val description: String,
)