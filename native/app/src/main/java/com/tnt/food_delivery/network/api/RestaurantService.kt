package com.tnt.food_delivery.network.api

import com.tnt.food_delivery.data.response.ProductResponse
import com.tnt.food_delivery.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantService {
    @GET("product")
    suspend fun searchProduct(
        @Query("name") name: String,
        @Query("status") status: String = "LAUNCH"
    ): Response<List<ProductResponse>>

    @GET("user/restaurant")
    suspend fun searchRestaurant(@Query("name") name: String): Response<List<UserResponse>>
}