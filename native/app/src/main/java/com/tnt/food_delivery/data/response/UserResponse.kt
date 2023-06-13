package com.tnt.food_delivery.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("firstName")
    val firstName: String? = null,

    @field:SerializedName("lastName")
    val lastName: String? = null,

    @field:SerializedName("isMale")
    val isMale: Boolean? = null,

    @field: SerializedName("birthOfDate")
    val birthOfDate: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("phoneNumber")
    val phoneNumber: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("userRole")
    val userRole: String = "USER"
)
