package com.tnt.food_delivery.presentation.sign_up

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.food_delivery.core.utils.EventResults
import com.tnt.food_delivery.core.utils.EventStatus
import com.tnt.food_delivery.data.response.AuthenticationResponse
import com.tnt.food_delivery.data.response.UserResponse
import com.tnt.food_delivery.network.api.AuthService
import com.tnt.food_delivery.network.di.NetworkModule
import kotlinx.coroutines.launch
import java.lang.Exception

class SignUpViewModel : ViewModel() {
    private val authService: AuthService = NetworkModule.provideAuthService()
    private val _authentication: MutableLiveData<EventResults<UserResponse>> =
        MutableLiveData(EventResults())
    val authentication: LiveData<EventResults<UserResponse>> = _authentication

    suspend fun signUp(body: Map<String, String>) {
        Log.d("test", "login")
        _authentication.value = EventResults(status = EventStatus.LOADING)
        viewModelScope.launch {
            try {
                val data = authService.signup(body)
                if (data.isSuccessful) {
                    _authentication.value =
                        EventResults(status = EventStatus.SUCCESS, data = data.body())
                    Log.d("data", data.body().toString())
                } else {
                    val errMsg = data.errorBody()?.string() ?: run { data.code().toString() }
                    _authentication.value =
                        EventResults(status = EventStatus.ERROR, error = errMsg)
                    Log.d("error data", errMsg)
                }
            } catch (e: Exception) {
                println(e.message.toString())
                _authentication.value = EventResults(status = EventStatus.ERROR, error = e.message)
                Log.d("error", e.message.toString())
            }

        }
    }
}