package com.tnt.food_delivery.network.service

import com.tnt.food_delivery.core.utils.constants.Constants.BASE_URL_V1
import com.tnt.food_delivery.data.response.AuthenticationResponse
import com.tnt.food_delivery.data.response.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {
//    @Headers("Content-Type: application/json")
    @POST("user/login")
    suspend fun login(@Body body: Map<String, String>): Response<AuthenticationResponse>

    @POST("user/signup")
    suspend fun signup(@Body body: Map<String, Any>): Response<UserResponse>

    companion object {
        private var retrofitService: APIService? = null
        fun getInstance(): APIService {
            if (retrofitService == null) {
                val client = OkHttpClient
                    .Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_V1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(APIService::class.java)
            }
            return retrofitService!!
        }
    }
}