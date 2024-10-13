package com.brs.foodapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brs.foodapp.data.model.CartItem
import com.brs.foodapp.data.model.Food
import com.brs.foodapp.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (private val cartRepository: CartRepository) : ViewModel() {
    fun addToCart(food: Food, quantity: Int, userName: String) {
        viewModelScope.launch {
            val cartItem = CartItem(0, food.yemek_adi, food.yemek_resim_adi, food.yemek_fiyat, quantity, userName)
            cartRepository.addToCart(cartItem)
        }
    }
}