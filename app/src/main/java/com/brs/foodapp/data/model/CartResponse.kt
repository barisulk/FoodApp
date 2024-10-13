package com.brs.foodapp.data.model

data class CartResponse(
    val sepet_yemekler: List<CartItem>,
    val success: Int
)