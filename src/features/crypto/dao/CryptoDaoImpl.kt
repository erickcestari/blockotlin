package com.blockotlin.features.crypto.dao

import com.blockotlin.features.authentication.dao.mapper.AuthenticationMapper
import com.blockotlin.features.crypto.model.AddCryptoDto

class CryptoDaoImpl(private val mapper: AuthenticationMapper) : CryptoDao {
    override fun addCrypto(request: AddCryptoDto) {
        TODO("Not yet implemented")
    }
}