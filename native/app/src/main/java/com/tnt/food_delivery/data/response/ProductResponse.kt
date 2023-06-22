package com.tnt.food_delivery.data.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("restaurant")
    val restaurant: UserResponse? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("isSize")
    val isSize: Boolean? = null,

    @field:SerializedName("createAt")
    val createAt: String? = null,

    @field:SerializedName("updateAt")
    val updateAt: String? = null,

    @field:SerializedName("price")
    val price: Long? = null,

    @field:SerializedName("s")
    val s: Long? = null,

    @field:SerializedName("m")
    val m: Long? = null,

    @field:SerializedName("l")
    val l: Long? = null,
)
