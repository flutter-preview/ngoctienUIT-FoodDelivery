package com.tnt.food_delivery.network.service

interface Service {
    fun sendMessage(data: String)
    fun closeService()
}