package com.tnt.food_delivery.presentation.sign_up

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.food_delivery.core.utils.EventResults
import com.tnt.food_delivery.core.utils.EventStatus
import com.tnt.food_delivery.data.response.RegisterResponse
import com.tnt.food_delivery.network.api.AuthService
import com.tnt.food_delivery.network.di.NetworkModule
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class SignUpViewModel : ViewModel() {
    private val authService: AuthService = NetworkModule.provideAuthService()
    private val _register: MutableLiveData<EventResults<RegisterResponse>> =
        MutableLiveData(EventResults())
    val register: LiveData<EventResults<RegisterResponse>> = _register

    suspend fun checkRegister(body: Map<String, String>) {
        Log.d("test", "sign up")
        _register.value = EventResults(status = EventStatus.LOADING)
        viewModelScope.launch {
            try {
                val data = authService.checkRegister(body)
                if (data.isSuccessful) {
                    if (data.body()!!.status) {
                        _register.value =
                            EventResults(status = EventStatus.SUCCESS, data = data.body())
                        delay(1000)
                        _register.value = EventResults()
                    } else {
                        _register.value =
                            EventResults(status = EventStatus.ERROR, error = data.body()!!.message)
                    }
                    Log.d("data", data.body().toString())
                } else {
                    val errMsg = data.errorBody()?.string() ?: run { data.code().toString() }
                    _register.value =
                        EventResults(status = EventStatus.ERROR, error = errMsg)
                    Log.d("error data", errMsg)
                }
            } catch (e: Exception) {
                println(e.message.toString())
                _register.value = EventResults(status = EventStatus.ERROR, error = e.message)
                Log.d("error", e.message.toString())
            }
        }
    }
}