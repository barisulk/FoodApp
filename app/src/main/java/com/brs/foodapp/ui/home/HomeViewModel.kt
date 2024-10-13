package com.brs.foodapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brs.foodapp.data.model.Food
import com.brs.foodapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor (private val foodRepository: FoodRepository) : ViewModel() {
    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> get() = _foods

    fun fetchAllFoods() {
        viewModelScope.launch {
            val response = foodRepository.getAllFoods()
            if (response.isSuccessful) {
                _foods.postValue(response.body()?.yemekler)
            }else{
                Log.e("HomeViewModel", "Error: ${response.errorBody()?.string()}")
            }
        }
    }
}