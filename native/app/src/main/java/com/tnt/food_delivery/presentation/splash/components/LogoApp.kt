package com.tnt.food_delivery.presentation.splash.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tnt.food_delivery.R
import com.tnt.food_delivery.ui.theme.Inter

@OptIn(ExperimentalTextApi::class)
@Composable
fun LogoApp() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_login),
            contentDescription = "icon logo",
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = "Food TNT",
            fontFamily = FontFamily(Font(R.font.viga_regular)),
            fontSize = 40.sp,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF53E88B), Color(0xFF15BE77))
                )
            )
        )
        Text(
            text = "Delivery Favorite Food",
            fontFamily = Inter,
            fontWeight = FontWeight(600),
            fontSize = 13.sp,
        )
    }
}