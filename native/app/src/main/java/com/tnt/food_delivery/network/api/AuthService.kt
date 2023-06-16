package com.tnt.food_delivery.network.api

import com.tnt.food_delivery.data.response.AuthenticationResponse
import com.tnt.food_delivery.data.response.Response
import com.tnt.food_delivery.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    //    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun login(@Body body: Map<String, String>): Call<Response<AuthenticationResponse>>

    @POST("user/signup")
    fun signup(@Body body: Map<String, Any>): Call<Response<UserResponse>>
}