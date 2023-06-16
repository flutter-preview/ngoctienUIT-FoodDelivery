package com.tnt.food_delivery.presentation.sign_in

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.food_delivery.core.onEmpty
import com.tnt.food_delivery.core.onLoading
import com.tnt.food_delivery.core.utils.StateUtils.update
import com.tnt.food_delivery.network.use_case.LoginUseCase
import ru.tech.cookhelper.presentation.ui.utils.event.Event
import ru.tech.cookhelper.presentation.ui.utils.event.ViewModelEvents
import ru.tech.cookhelper.presentation.ui.utils.event.ViewModelEventsImpl

class SignInViewModel(private val loginUseCase: LoginUseCase) : ViewModel(),
    ViewModelEvents<Event> by ViewModelEventsImpl() {
    private var _signInState: MutableState<SignInState> = mutableStateOf(SignInState())
    val signInState: SignInState by _signInState

    fun login(username: String, password: String) {
        Log.d("test", "login")
        loginUseCase(username, password).onLoading {
            _signInState.update { SignInState(isLoading = true) }
        }
            .onEmpty {
                _signInState.update { SignInState() }
                sendEvent(
                    Event.ShowToast(
                        "getUIText()",
                        Icons.Rounded.ErrorOutline
                    )
                )
            }
            .onError {
                _signInState.update { SignInState() }
                sendEvent(
                    Event.ShowToast(
                        "UIText.DynamicString(this)",
                        Icons.Rounded.ErrorOutline
                    )
                )
            }
            .onSuccess {
                sendEvent(
                    Event.SendData(
                        "email" to email,
                        "name" to name,
                        "token" to token
                    )
                )
                if (!verified) sendEvent(
                    Event.NavigateTo(Screen.Authentication.Confirmation)
                )
                else {
                    sendEvent(
                        Event.ShowToast(
                            UIText.StringResource(
                                R.string.welcome_user,
                                name
                            ), Icons.Outlined.Face
                        )
                    )
                    sendEvent(
                        Event.NavigateIf(
                            predicate = { it is Screen.Authentication },
                            screen = Screen.Home.None
                        )
                    )
//                    cacheUserUseCase(this)
                }
                _signInState.update { SignInState(user = this@onSuccess) }
            }
            .launchIn(viewModelScope)

    }
}
