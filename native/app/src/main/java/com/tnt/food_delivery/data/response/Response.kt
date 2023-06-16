package com.tnt.food_delivery.data.response

data class Response<T>(
    val status: Int,
    val data: T?
)