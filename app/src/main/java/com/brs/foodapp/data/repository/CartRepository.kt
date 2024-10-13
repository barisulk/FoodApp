package com.brs.foodapp.data.repository

import com.brs.foodapp.data.model.CartItem
import com.brs.foodapp.data.remote.ApiService
import javax.inject.Inject

class CartRepository @Inject constructor (private val apiService: ApiService) {
    suspend fun addToCart(cartItem: CartItem) = apiService.addToCart(
        cartItem.yemek_adi,
        cartItem.yemek_resim_adi,
        cartItem.yemek_fiyat,
        cartItem.yemek_siparis_adet,
        cartItem.kullanici_adi
    )

    suspend fun getCartItems(kullaniciAdi: String) = apiService.getCartItems(kullaniciAdi)

    suspend fun removeFromCart(sepetYemekId: Int, kullaniciAdi: String) =
        apiService.removeFromCart(sepetYemekId, kullaniciAdi)
}