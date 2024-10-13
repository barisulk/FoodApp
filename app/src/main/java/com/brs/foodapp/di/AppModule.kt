package com.brs.foodapp.di

import com.brs.foodapp.data.remote.ApiService
import com.brs.foodapp.data.repository.CartRepository
import com.brs.foodapp.data.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// di/AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://kasimadalan.pe.hu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFoodRepository(apiService: ApiService): FoodRepository {
        return FoodRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideCartRepository(apiService: ApiService): CartRepository {
        return CartRepository(apiService)
    }
}
