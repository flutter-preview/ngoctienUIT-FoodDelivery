package com.tnt.food_delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.core.utils.constants.NavDestinations
import com.tnt.food_delivery.presentation.filter.FilterScreen
import com.tnt.food_delivery.presentation.main.MainScreen
import com.tnt.food_delivery.presentation.sign_in.SignInScreen
import com.tnt.food_delivery.presentation.sign_up.SignUpScreen
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyApp() }
    }
}

@Composable
@OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)
fun MyApp() {
    FoodDeliveryTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = NavDestinations.SIGNIN_SCREEN,
            ) {
                composable(NavDestinations.SIGNIN_SCREEN) {
                    SignInScreen(navController)
                }
                composable(NavDestinations.SIGNUP_SCREEN) {
                    SignUpScreen(navController)
                }
                composable(NavDestinations.MAIN_SCREEN) {
                    MainScreen(navController)
                }
                composable(NavDestinations.FILTER_SCREEN) {
                    FilterScreen(navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodDeliveryTheme {
        MyApp()
    }
}