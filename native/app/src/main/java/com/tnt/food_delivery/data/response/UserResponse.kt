package com.tnt.food_delivery.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("avatar")
    val avatar: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("isMale")
    val isMale: Boolean = true,

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

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("wards")
    val wards: String? = null,

    @field:SerializedName("district")
    val district: String? = null,

    @field:SerializedName("province")
    val province: String? = null,

    @field:SerializedName("userRole")
    val userRole: String = "USER",

    @field:SerializedName("status")
    val status: String = "ACTIVATED",
)
