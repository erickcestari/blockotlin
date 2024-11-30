package com.blockotlin.features.cart.data

import com.blockotlin.features.cart.model.ProductCartInfoDto
import com.blockotlin.features.cart.model.SetProductCardDto

interface CartData {
    fun setProductCart(request: SetProductCardDto)
    fun getUserCart(userId: Long): List<ProductCartInfoDto>
}