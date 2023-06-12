package com.tnt.food_delivery.model.response

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @field:SerializedName("accessToken")
    val token: String? = null,

    @field:SerializedName("user")
    val user: UserResponse? = null
)