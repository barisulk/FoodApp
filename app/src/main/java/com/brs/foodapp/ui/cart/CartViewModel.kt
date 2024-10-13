package com.brs.foodapp.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brs.foodapp.data.model.CartItem
import com.brs.foodapp.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor (private val cartRepository: CartRepository) : ViewModel() {
    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> get() = _cartItems

    fun fetchCartItems(userName: String) {
        viewModelScope.launch {
            val response = cartRepository.getCartItems(userName)
            if (response.isSuccessful) {
                _cartItems.postValue(response.body()?.sepet_yemekler)
                val items = response.body()?.sepet_yemekler
                _cartItems.postValue(items!!)
                Log.d("CartViewModel", "Sepet öğeleri: $items")
            }else{
                Log.e("CartViewModel", "Hata: ${response.message()}")
            }
        }
    }

    fun removeItem(sepetYemekId: Int, userName: String) {
        viewModelScope.launch {
            cartRepository.removeFromCart(sepetYemekId, userName)
            fetchCartItems(userName)
        }
    }
}