package com.tnt.food_delivery.network.di

import com.tnt.food_delivery.network.api.AuthService
import com.tnt.food_delivery.network.api.UserApi
import javax.inject.Singleton

object NetworkModule {
    @Singleton
    private val retrofit = RetrofitModule.provideRetrofit()

    @Singleton
    fun provideUserApi(): UserApi = retrofit.create(UserApi::class.java)

    @Singleton
    fun provideAuthService(): AuthService = retrofit.create(AuthService::class.java)
}