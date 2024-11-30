package com.blockotlin.features.cart.data

import com.blockotlin.features.cart.dao.CartDao
import com.blockotlin.features.cart.model.ProductCartInfoDto
import com.blockotlin.features.cart.model.SetProductCardDto

class CartDataImpl(private val cartDao: CartDao) :
    CartData {
    override fun setProductCart(request: SetProductCardDto) {
        cartDao.setProductCart(request)
    }

    override fun getUserCart(userId: Long): List<ProductCartInfoDto> {
        return cartDao.getUserCart(userId)
    }
}