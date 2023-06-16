package com.tnt.food_delivery.core.di

import com.tnt.food_delivery.network.api.UserApi
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

object NetworkModule {
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)
}