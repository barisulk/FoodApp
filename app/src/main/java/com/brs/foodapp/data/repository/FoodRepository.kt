package com.brs.foodapp.data.repository

import com.brs.foodapp.data.remote.ApiService
import javax.inject.Inject

class FoodRepository @Inject constructor (private val apiService: ApiService) {
    suspend fun getAllFoods() = apiService.getFoodItems()
}