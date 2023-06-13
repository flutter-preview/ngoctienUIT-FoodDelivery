package com.tnt.food_delivery.presentation.sign_in

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.food_delivery.data.response.AuthenticationResponse
import com.tnt.food_delivery.network.service.APIService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val apiService: APIService = APIService.getInstance()
    private val _authentication: MutableLiveData<AuthenticationResponse?> = MutableLiveData(null)
    val authentication: LiveData<AuthenticationResponse?> = _authentication

    fun login(body: Map<String, String>) {
        Log.d("test", "login")
        viewModelScope.launch {
            val login = async { apiService.login(body) }
            val response = login.await()
            _authentication.value = response.body()
            Log.d("data", response.body().toString())
        }
    }
}
