package com.tnt.food_delivery.data.response

import com.google.gson.annotations.SerializedName
import com.tnt.food_delivery.data.Dto
import com.tnt.food_delivery.data.model.User
import com.tnt.food_delivery.network.utils.Domain

data class AuthenticationResponse(
    @field:SerializedName("accessToken")
    val token: String? = null,

    @field:SerializedName("user")
    val user: UserResponse? = null
) : Dto {
    override fun asDomain(): User {
        return User()
    }

}