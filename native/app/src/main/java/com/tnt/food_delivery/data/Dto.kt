package com.tnt.food_delivery.data

import com.tnt.food_delivery.network.utils.Domain

interface Dto {
    fun asDomain(): Domain
}