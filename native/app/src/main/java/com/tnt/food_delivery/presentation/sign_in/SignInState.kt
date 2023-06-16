package com.tnt.food_delivery.presentation.sign_in

import com.tnt.food_delivery.data.model.User

data class SignInState(
    val isLoading: Boolean = false,
    val user: User? = null
)
