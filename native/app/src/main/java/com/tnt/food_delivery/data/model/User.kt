package com.tnt.food_delivery.data.model

import com.tnt.food_delivery.network.utils.Domain

data class User(
    val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val isMale: Boolean? = null,
    val birthOfDate: String? = null,
    val username: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val password: String? = null,
    val userRole: String = "USER"
): Domain
