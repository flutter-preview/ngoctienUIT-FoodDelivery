package com.tnt.food_delivery.network.repository

import com.tnt.food_delivery.core.Action
import com.tnt.food_delivery.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun loginWith(login: String, password: String): Flow<Action<User>>

    fun registerWith(
        name: String,
        surname: String,
        nickname: String,
        email: String,
        password: String
    ): Flow<Action<User>>
}