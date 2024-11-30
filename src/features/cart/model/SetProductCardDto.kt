package com.blockotlin.features.cart.model

data class SetProductCardDto (
    var userId: Long,
    val productId: Long,
    val quantity: Int
)