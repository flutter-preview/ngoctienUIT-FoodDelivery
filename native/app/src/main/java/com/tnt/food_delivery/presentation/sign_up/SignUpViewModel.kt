package com.tnt.food_delivery.presentation.sign_up

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    var email: MutableState<String> = mutableStateOf("")
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    var username: MutableState<String> = mutableStateOf("")
    var usernameErrMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    var confirmPassword: MutableState<String> = mutableStateOf("")
    var confirmPasswordErrMsg: MutableState<String> = mutableStateOf("")

    var isEnableButton: MutableState<Boolean> = mutableStateOf(false)

    suspend fun checkRegister() {
        Log.d("test", "sign up")
        _register.value = EventResults(status = EventStatus.LOADING)
        viewModelScope.launch {
            try {
                val data = authService.checkRegister(
                    mapOf(
                        "username" to username.value,
                        "email" to email.value
                    )
                )
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

    fun validateEmail() {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            emailErrMsg.value = "Email không đúng định dạng"
        } else {
            emailErrMsg.value = ""
        }
        enableButton()
    }

    fun validateUsername() {
        if (username.value.isEmpty()) {
            usernameErrMsg.value = "Vui lòng nhập vào username"
        } else {
            usernameErrMsg.value = ""
        }
        enableButton()
    }

    fun validatePassword() {
        android.util.Patterns.EMAIL_ADDRESS
        if (password.value.length < 8) {
            passwordErrMsg.value = "Độ dài mật khẩu tối thiểu 8 ký tự"
        } else {
            passwordErrMsg.value = ""
        }
        enableButton()
    }

    fun validateConfirmPassword() {
        android.util.Patterns.EMAIL_ADDRESS
        if (confirmPassword.value != password.value) {
            confirmPasswordErrMsg.value = "Mật khẩu xác nhận không trùng khớp"
        } else {
            confirmPasswordErrMsg.value = ""
        }
        enableButton()
    }

    private fun enableButton() {
        val check: Boolean =
            passwordErrMsg.value.isEmpty() && emailErrMsg.value.isEmpty() && usernameErrMsg.value.isEmpty()
                    && email.value.isNotEmpty() && password.value.isNotEmpty() && username.value.isNotEmpty()
        if (isEnableButton.value != check) isEnableButton.value = check
    }
}