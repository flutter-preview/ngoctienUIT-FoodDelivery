package com.tnt.food_delivery.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.food_delivery.core.utils.EventResults
import com.tnt.food_delivery.core.utils.EventStatus
import com.tnt.food_delivery.data.response.ProductResponse
import com.tnt.food_delivery.data.response.UserResponse
import com.tnt.food_delivery.network.api.RestaurantService
import com.tnt.food_delivery.network.di.NetworkModule
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {
    private val restaurantService: RestaurantService = NetworkModule.provideRestaurantService()
    private val _restaurant: MutableLiveData<EventResults<List<UserResponse>>> =
        MutableLiveData(EventResults())
    val restaurant: LiveData<EventResults<List<UserResponse>>> = _restaurant

    private val _product: MutableLiveData<EventResults<List<ProductResponse>>> =
        MutableLiveData(EventResults())
    val product: LiveData<EventResults<List<ProductResponse>>> = _product

    suspend fun getAllRestaurant() {
        Log.d("test", "get all restaurant")
        _restaurant.value = EventResults(status = EventStatus.LOADING)
        viewModelScope.launch {
            try {
                val data = restaurantService.searchRestaurant("")
                if (data.isSuccessful) {
                    _restaurant.value =
                        EventResults(status = EventStatus.SUCCESS, data = data.body())
//                    delay(1000)
//                    _restaurant.value = EventResults()
                    Log.d("data", data.body().toString())
                } else {
                    val errMsg = data.errorBody()?.string() ?: run { data.code().toString() }
                    _restaurant.value =
                        EventResults(status = EventStatus.ERROR, error = errMsg)
                    Log.d("error data", errMsg)
                }
            } catch (e: Exception) {
                println(e.message.toString())
                _restaurant.value = EventResults(status = EventStatus.ERROR, error = e.message)
                Log.d("error", e.message.toString())
            }
        }
    }

    suspend fun getAllProduct() {
        Log.d("test", "get all product")
        _product.value = EventResults(status = EventStatus.LOADING)
        viewModelScope.launch {
            try {
                val data = restaurantService.searchProduct("")
                if (data.isSuccessful) {
                    _product.value =
                        EventResults(status = EventStatus.SUCCESS, data = data.body())
//                    delay(1000)
//                    _product.value = EventResults()
                    Log.d("data", data.body().toString())
                } else {
                    val errMsg = data.errorBody()?.string() ?: run { data.code().toString() }
                    _product.value =
                        EventResults(status = EventStatus.ERROR, error = errMsg)
                    Log.d("error data", errMsg)
                }
            } catch (e: Exception) {
                println(e.message.toString())
                _product.value = EventResults(status = EventStatus.ERROR, error = e.message)
                Log.d("error", e.message.toString())
            }
        }
    }
}