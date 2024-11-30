package com.blockotlin.features.cart.dao

import com.blockotlin.features.cart.model.ProductCartInfoDto
import com.blockotlin.features.cart.model.SetProductCardDto

interface CartDao {
    fun setProductCart(request: SetProductCardDto)
    fun getUserCart(userId: Long): List<ProductCartInfoDto>
}