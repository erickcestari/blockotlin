package com.blockotlin.features.crypto.data

import com.blockotlin.features.crypto.model.AddCryptoDto

interface CryptoData {
    fun addCrypto(request: AddCryptoDto): String
}