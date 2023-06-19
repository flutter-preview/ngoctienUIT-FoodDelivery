package com.tnt.food_delivery.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("status")
    val status: Boolean,

    @field:SerializedName("message")
    val message: String
)