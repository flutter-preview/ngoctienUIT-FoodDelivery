package com.tnt.food_delivery.presentation.sign_in

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.core.utils.constants.NavDestinations
import com.tnt.food_delivery.data.DataStoreManager
import com.tnt.food_delivery.presentation.onboarding.components.GradientButton
import com.tnt.food_delivery.presentation.sign_in.components.shadow
import com.tnt.food_delivery.presentation.splash.components.LogoApp
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import kotlinx.coroutines.launch
import ru.tech.cookhelper.presentation.ui.utils.event.Event
import ru.tech.cookhelper.presentation.ui.utils.event.collectWithLifecycle
import java.lang.Exception
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@ExperimentalTextApi
@ExperimentalMaterial3Api
@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel = hiltViewModel()) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    val signInUIState by viewModel.signInState.collectAsState()
//    val authentication by viewModel.authentication.observeAsState(initial = null)
    val dataStoreManager: DataStoreManager = DataStoreManager(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()


//    if (authentication != null) {
//        LaunchedEffect(true)
//        {
//            coroutineScope.launch {
//                authentication!!.token?.let { dataStoreManager.setString("token", it) }
//                authentication!!.user!!.id?.let { it1 -> dataStoreManager.setString("userID", it1) }
//            }
//            navController.navigate(NavDestinations.MAIN_SCREEN)
//            {
//                popUpTo(NavDestinations.SIGNIN_SCREEN) { inclusive = true }
//            }
//            Log.d("sign in data", authentication.toString())
//        }
//
//    }

    viewModel.eventFlow.collectWithLifecycle {
        when (it) {
            is Event.ShowToast -> toastHost.show(
                it.icon,
                it.text.asString(context)
            )
            is Event.SendData -> {
                onGetCredentials(it["name"], it["email"], it["token"])
            }
            is Event.NavigateTo -> {
                authController.navigate(it.screen)
            }
            is Event.NavigateIf -> {
                if (it.predicate(screenController.currentDestination)) {
                    screenController.navigate(it.screen)
                    onTitleChange(Screen.Home.Feed.title)
                }
            }
            else -> {}
        }
    }

    Scaffold {
        it
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.background_light),
                contentDescription = "tnt"
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                LogoApp()
                Spacer(modifier = Modifier.height(60.dp))
                Text(text = "Login To Your Account", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(40.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .border(
                            width = 2.dp, color = Color(0xFFF4F4F4), shape = RoundedCornerShape(30),
                        )
                        .shadow(
                            color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                            spread = 20.dp,
                            blurRadius = 50.dp,
                            offsetX = 12.dp,
                            offsetY = 26.dp,
                        ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                    value = username,
                    onValueChange = { text -> username = text },
                    placeholder = { Text(text = "Username") },
                    shape = RoundedCornerShape(30),
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .border(
                            width = 2.dp, color = Color(0xFFF4F4F4), shape = RoundedCornerShape(30),
                        )
                        .shadow(
                            color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                            spread = 20.dp,
                            blurRadius = 50.dp,
                            offsetX = 12.dp,
                            offsetY = 26.dp,
                        ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                    value = password,
                    onValueChange = { text -> password = text },
                    placeholder = { Text(text = "Password") },
                    shape = RoundedCornerShape(30),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Or Continue With", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    CustomSocialButton(text = "Facebook", id = R.drawable.facebook_logo)
                    CustomSocialButton(text = "Google", id = R.drawable.google_logo)
                }
                Spacer(modifier = Modifier.height(20.dp))
                TextButton(onClick = { }) {
                    Text(
                        text = "Forgot Your Password?", style = TextStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF53E88B), Color(0xFF15BE77))
                            )
                        )
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))
                GradientButton(
                    text = "Login",
                    onClick = {
                        try {
                            viewModel.login(username.text, password.text)
                        } catch (e: Exception) {
                            println(e.message.toString())
                            Log.d("error", e.message.toString())
                        }
                    },
                    modifier = Modifier
                        .height(56.dp)
                        .width(157.dp),
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextButton(onClick = {
                    navController.navigate(NavDestinations.SIGNUP_SCREEN)
                    {
                        popUpTo(NavDestinations.SIGNIN_SCREEN) { inclusive = true }
                    }
                }) {
                    Text(
                        text = "You don't have an account?", style = TextStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF53E88B), Color(0xFF15BE77))
                            )
                        )
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun CustomSocialButton(text: String, onClick: () -> Unit = { }, id: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(57.dp)
            .width(152.dp)
            .clickable { onClick() }
            .border(
                width = 2.dp, color = Color(0xFFF4F4F4), shape = RoundedCornerShape(30),
            )
            .background(color = Color.White),
    ) {
        Image(
            modifier = Modifier.height(30.dp),
            painter = painterResource(id = id),
            contentDescription = "tnt"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@ExperimentalTextApi
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    FoodDeliveryTheme {
        SignInScreen(rememberNavController())
    }
}