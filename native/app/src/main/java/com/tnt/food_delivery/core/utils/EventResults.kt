package com.tnt.food_delivery.core.utils

data class EventResults<T>(
    val data: T? = null,
    val status: EventStatus? = null,
    val error: String? = null
)