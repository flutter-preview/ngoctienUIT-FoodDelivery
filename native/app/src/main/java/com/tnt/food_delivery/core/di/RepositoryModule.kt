package com.tnt.food_delivery.core.di

import com.tnt.food_delivery.data.repository.UserRepositoryImpl
import com.tnt.food_delivery.network.api.AuthService
import com.tnt.food_delivery.network.api.UserApi
import com.tnt.food_delivery.network.repository.UserRepository
import com.tnt.food_delivery.network.service.user.UserService
import dagger.Provides
import javax.inject.Singleton

object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        authService: AuthService,
        userApi: UserApi,
//        db: Database,
//        feedService: FeedService,
        userService: UserService
    ): UserRepository = UserRepositoryImpl(
        authService = authService,
        userApi = userApi,
//        userDao = db.userDao,
//        feedService = feedService,
        userService = userService
    )
}