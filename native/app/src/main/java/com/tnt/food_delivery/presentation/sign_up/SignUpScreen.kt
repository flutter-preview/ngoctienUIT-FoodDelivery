package com.tnt.food_delivery.presentation.sign_up

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.core.components.ShowLoading
import com.tnt.food_delivery.core.components.showToast
import com.tnt.food_delivery.core.utils.EventResults
import com.tnt.food_delivery.core.utils.EventStatus
import com.tnt.food_delivery.core.utils.NavDestinations
import com.tnt.food_delivery.presentation.onboarding.components.GradientButton
import com.tnt.food_delivery.presentation.sign_in.components.shadow
import com.tnt.food_delivery.presentation.splash.components.LogoApp
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import kotlinx.coroutines.launch

@ExperimentalTextApi
@ExperimentalMaterial3Api
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.register.observeAsState()

    LaunchedEffect(state)
    {
        Log.d("check", "ok")
        when (state!!.status) {
            EventStatus.SUCCESS -> {
                navController.navigate("${NavDestinations.SIGNUP_PROCESS_SCREEN}/$username/$email/$password") {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) { saveState = true }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                Log.d("sign up data", state!!.data.toString())
            }

            EventStatus.LOADING -> {
                Log.d("Loading", "Loading")
            }

            EventStatus.ERROR -> {
                Log.d("Error", state!!.error ?: "Lỗi bất định")
                showToast(context, state!!.error ?: "Lỗi bất định");
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
                Text(text = "Sign Up For Free", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(40.dp))
                CustomTextField(
                    icon = R.drawable.icon_profile,
                    value = username,
                    onValueChange = { value -> username = value },
                    placeholder = "Username"
                )
                Spacer(modifier = Modifier.height(12.dp))
                CustomTextField(
                    icon = R.drawable.icon_email,
                    value = email,
                    onValueChange = { value -> email = value },
                    placeholder = "Email"
                )
                Spacer(modifier = Modifier.height(12.dp))
                CustomTextField(
                    icon = R.drawable.icon_lock,
                    value = password,
                    onValueChange = { value -> password = value },
                    placeholder = "Password"
                )
                Spacer(modifier = Modifier.height(44.dp))
                GradientButton(
                    text = "Create Account",
                    modifier = Modifier
                        .height(56.dp)
                        .width(157.dp),
                    onClick = {
                        coroutineScope.launch {
                            viewModel.checkRegister(
                                mapOf(
                                    "username" to username,
                                    "email" to email
                                )
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextButton(onClick = {
                    navController.navigate(NavDestinations.SIGNIN_SCREEN)
                    {
                        popUpTo(NavDestinations.SIGNUP_SCREEN) { inclusive = true }
                    }
                }) {
                    Text(
                        text = "Already have an account?", style = TextStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF53E88B), Color(0xFF15BE77))
                            )
                        )
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                if (state!!.status == EventStatus.LOADING) ShowLoading()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 25.dp)
        .border(
            width = 1.dp, color = Color(0xFFF4F4F4), shape = RoundedCornerShape(30),
        )
        .shadow(
            color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
            spread = 20.dp,
            blurRadius = 50.dp,
            offsetX = 12.dp,
            offsetY = 26.dp,
        ),
    icon: Int,
    placeholder: String,
    value: String,
    onValueChange: (value: String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, color = Color(0xFF3B3B3B).copy(alpha = 0.3f)) },
        shape = RoundedCornerShape(30),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        leadingIcon = {
            Image(
                modifier = Modifier.height(30.dp),
                painter = painterResource(id = icon),
                contentDescription = "icon profile",
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    FoodDeliveryTheme {
        SignUpScreen(rememberNavController())
    }
}