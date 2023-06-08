package com.tnt.food_delivery.presentation.payment_method

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.food_delivery.R
import com.tnt.food_delivery.presentation.onboarding.components.GradientButton
import com.tnt.food_delivery.presentation.sign_in.components.shadow
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen() {
    Scaffold() {
        it
        Box() {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.triangle_background),
                contentDescription = "tnt"
            )
            Column(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp),
            ) {
                Card(
                    modifier = Modifier.padding(top = 38.dp, bottom = 20.dp),
                    shape = RoundedCornerShape(15),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF9A84D).copy(alpha = 0.1f))
                ) {
                    Box(
                        modifier = Modifier
                            .width(45.dp)
                            .height(45.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back Icon",
                            tint = Color(0xFFDA6317)
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(end = 60.dp),
                    text = "Payment Method",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.padding(end = 90.dp),
                    text = "This data will be displayed in your account profile for security",
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomPaymentMethod(id = R.drawable.paypal_icon, onClick = {})
                Spacer(modifier = Modifier.height(20.dp))
                CustomPaymentMethod(id = R.drawable.visa_icon, onClick = {})
                Spacer(modifier = Modifier.height(20.dp))
                CustomPaymentMethod(id = R.drawable.payoneer_icon, onClick = {})
                Spacer(modifier = Modifier.height(50.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    GradientButton(modifier = Modifier
                        .height(56.dp)
                        .width(157.dp), text = "Next", onClick = {})
                }
            }
        }
    }
}

@Composable
fun CustomPaymentMethod(onClick: () -> Unit = { }, id: Int) {
    Card(
        modifier = Modifier
            .shadow(
                color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                spread = 25.dp,
                blurRadius = 50.dp,
                offsetX = 12.dp,
                offsetY = 26.dp,
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(22),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(73.dp)
                .background(color = Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = id),
                contentDescription = "TNT"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentMethodPreview() {
    FoodDeliveryTheme {
        PaymentMethodScreen()
    }
}