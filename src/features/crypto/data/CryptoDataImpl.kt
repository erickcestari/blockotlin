package com.blockotlin.features.crypto.data

import com.blockotlin.features.crypto.model.AddCryptoDto


class CryptoDataImpl(private val cryptoData: CryptoData) :
    CryptoData {
    override fun addCrypto(request: AddCryptoDto): String {
        throw Exception("Not yet implemented")

        return ""
    }
}