package com.tnt.food_delivery.presentation.sign_up_success

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.core.utils.NavDestinations
import com.tnt.food_delivery.presentation.onboarding.components.GradientButton
import com.tnt.food_delivery.presentation.sign_in.components.shadow
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import com.tnt.food_delivery.ui.theme.Viga

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun SignUpSuccessScreen(navController: NavController) {
    Scaffold {
        it
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.success_background),
                contentDescription = "tnt"
            )
            Column(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.25f))
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 100.dp),
                    painter = painterResource(id = R.drawable.success_icon),
                    contentDescription = "tnt"
                )
                Text(
                    text = "Congrats!",
                    fontSize = 30.sp,
                    fontFamily = Viga,
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF53E88B), Color(0xFF15BE77))
                        )
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Your Profile Is Ready To Use",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(50.dp))
                GradientButton(
                    modifier = Modifier
                        .height(56.dp)
                        .width(157.dp),
                    text = "Go To Sign In",
                    onClick = {
                        navController.navigate(NavDestinations.SIGNIN_SCREEN) {
                            launchSingleTop = true
                            popUpTo(0) { inclusive = true }
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpSuccessPreview() {
    FoodDeliveryTheme {
        SignUpSuccessScreen(rememberNavController())
    }
}