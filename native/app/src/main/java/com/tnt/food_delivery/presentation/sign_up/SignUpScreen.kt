package com.tnt.food_delivery.presentation.sign_up

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.input.KeyboardType
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
import com.tnt.food_delivery.ui.components.GradientButton
import com.tnt.food_delivery.ui.components.shadow
import com.tnt.food_delivery.ui.components.LogoApp
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import kotlinx.coroutines.launch

@ExperimentalTextApi
@ExperimentalMaterial3Api
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.register.observeAsState()
    var isHidePassword by remember { mutableStateOf(true) }
    var isHideConfirmPassword by remember { mutableStateOf(true) }

    LaunchedEffect(state)
    {
        Log.d("check", "ok")
        when (state!!.status) {
            EventStatus.SUCCESS -> {
                navController.navigate("${NavDestinations.SIGNUP_PROCESS_SCREEN}/${viewModel.username.value}/${viewModel.email.value}/${viewModel.password.value}") {
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
                Text(text = "Sign Up For Free", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(40.dp))
                CustomTextField(
                    icon = R.drawable.icon_profile,
                    value = viewModel.username.value,
                    onValueChange = { value ->
                        viewModel.username.value = value
                        viewModel.validateUsername()
                    },
                    placeholder = "Username",
                    error = viewModel.usernameErrMsg.value
                )
                Spacer(modifier = Modifier.height(12.dp))
                CustomTextField(
                    icon = R.drawable.icon_email,
                    value = viewModel.email.value,
                    onValueChange = { value ->
                        viewModel.email.value = value
                        viewModel.validateEmail()
                    },
                    placeholder = "Email",
                    error = viewModel.emailErrMsg.value,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                )
                Spacer(modifier = Modifier.height(12.dp))
                CustomTextField(
                    icon = R.drawable.icon_lock,
                    value = viewModel.password.value,
                    onValueChange = { value ->
                        viewModel.password.value = value
                        viewModel.validatePassword()
                    },
                    placeholder = "Password",
                    error = viewModel.passwordErrMsg.value,
                    isHide = isHidePassword,
                    onChange = { isHidePassword = !isHidePassword },
                )
                Spacer(modifier = Modifier.height(12.dp))
                CustomTextField(
                    icon = R.drawable.icon_lock,
                    value = viewModel.confirmPassword.value,
                    onValueChange = { value ->
                        viewModel.confirmPassword.value = value
                        viewModel.validateConfirmPassword()
                    },
                    placeholder = "Confirm Password",
                    error = viewModel.confirmPasswordErrMsg.value,
                    isHide = isHideConfirmPassword,
                    onChange = { isHideConfirmPassword = !isHideConfirmPassword },
                )
                Spacer(modifier = Modifier.height(44.dp))
                GradientButton(
                    text = "Create Account",
                    modifier = Modifier
                        .height(56.dp)
                        .width(157.dp),
                    isEnable = viewModel.isEnableButton.value,
                    onClick = { coroutineScope.launch { viewModel.checkRegister() } }
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
    onValueChange: (value: String) -> Unit,
    error: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    isHide: Boolean? = null,
    onChange: (() -> Unit)? = null
) {
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            isError = error.isNotEmpty(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF3B3B3B).copy(alpha = 0.3f)
                )
            },
            shape = RoundedCornerShape(30),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            leadingIcon = {
                Image(
                    modifier = Modifier.height(30.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "icon profile",
                )
            },
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    FoodDeliveryTheme {
        SignUpScreen(rememberNavController())
    }
}