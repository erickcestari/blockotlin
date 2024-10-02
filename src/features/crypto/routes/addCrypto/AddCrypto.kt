package com.blockotlin.features.crypto.routes.addCrypto

import com.blockotlin.features.crypto.data.CryptoData
import com.blockotlin.features.crypto.model.AddCryptoDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.addCrypto() {
    val cryptoData: CryptoData by inject(CryptoData::class.java)
    post("/api/v1/crypto/addCrypto") {

        cryptoData.addCrypto(call.receive<AddCryptoDto>())
        call.respond(HttpStatusCode.OK)
    }
}