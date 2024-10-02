package com.blockotlin.features.crypto.dao

import com.blockotlin.features.crypto.model.AddCryptoDto

interface CryptoDao {
    fun addCrypto(request: AddCryptoDto)
}