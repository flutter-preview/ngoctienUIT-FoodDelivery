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
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.core.components.ShowLoading
import com.tnt.food_delivery.core.components.showToast
import com.tnt.food_delivery.core.utils.EventStatus
import com.tnt.food_delivery.core.utils.NavDestinations
import com.tnt.food_delivery.data.DataStoreManager
import com.tnt.food_delivery.ui.components.GradientButton
import com.tnt.food_delivery.ui.components.shadow
import com.tnt.food_delivery.ui.components.LogoApp
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import kotlinx.coroutines.launch

@ExperimentalTextApi
@ExperimentalMaterial3Api
@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel = hiltViewModel()) {
    val state by viewModel.authentication.observeAsState()
    val context = LocalContext.current
    val dataStoreManager = DataStoreManager(context)
    val coroutineScope = rememberCoroutineScope()
    var isHide by remember { mutableStateOf(true) }

    LaunchedEffect(state) {
        Log.d("check", "ok")
        when (state!!.status) {
            EventStatus.SUCCESS -> {
                coroutineScope.launch {
                    state!!.data!!.token?.let { dataStoreManager.setString("token", it) }
                    state!!.data!!.user!!.id?.let { id ->
                        dataStoreManager.setString("userID", id)
                    }
                }
                navController.navigate(NavDestinations.MAIN_SCREEN) {
                    popUpTo(NavDestinations.SIGNIN_SCREEN) { inclusive = true }
                }
                Log.d("sign in data", state!!.data.toString())
            }

            EventStatus.LOADING -> {
                Log.d("Loading", "Loading")
            }

            EventStatus.ERROR -> {
                Log.d("Error", state!!.error ?: "Lỗi bất định")
                showToast(context, state!!.error ?: "Lỗi bất định")
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
                CustomTextField(
                    placeholder = "Username",
                    value = viewModel.username.value,
                    onValueChange = { text ->
                        viewModel.username.value = text
                        viewModel.validateUsername()
                    },
                    error = viewModel.usernameErrMsg.value
                )
                Spacer(modifier = Modifier.height(12.dp))
                CustomTextField(
                    placeholder = "Password",
                    value = viewModel.password.value,
                    onValueChange = { text ->
                        viewModel.password.value = text
                        viewModel.validatePassword()
                    },
                    error = viewModel.passwordErrMsg.value,
                    isHide = isHide,
                    onChange = { isHide = !isHide },
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
                    onClick = { coroutineScope.launch { viewModel.login() } },
                    modifier = Modifier
                        .height(56.dp)
                        .width(157.dp),
                    isEnable = viewModel.isEnableButton.value
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
                if (state!!.status == EventStatus.LOADING) ShowLoading()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier
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
    placeholder: String,
    value: String,
    onValueChange: (value: String) -> Unit,
    error: String = "",
    isHide: Boolean? = null,
    onChange: (() -> Unit)? = null
) {
    Column {
        OutlinedTextField(
            modifier = modifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            shape = RoundedCornerShape(30),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            isError = error.isNotEmpty(),
            trailingIcon = if (isHide != null) {
                {
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { onChange?.let { it() } },
                        painter = painterResource(id = if (isHide) R.drawable.icon_eye_close else R.drawable.icon_eye_open),
                        contentDescription = ""
                    )
                }
            } else null,
            visualTransformation = if (isHide != null && isHide) PasswordVisualTransformation() else VisualTransformation.None,
        )
        if (error.isNotEmpty())
            Text(
                modifier = Modifier.padding(start = 35.dp),
                text = error,
                fontSize = 12.sp,
                color = Color.Red
            )
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