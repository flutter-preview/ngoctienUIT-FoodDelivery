package com.tnt.food_delivery.presentation.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.food_delivery.R
import com.tnt.food_delivery.presentation.onboarding.components.GradientButton
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import com.tnt.food_delivery.ui.theme.Inter

@ExperimentalTextApi
@ExperimentalMaterial3Api
@Composable
fun SignUp() {
    Scaffold() {
        it
        Box() {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.background_light),
                contentDescription = "tnt"
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_login),
                    contentDescription = "test",
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
                Spacer(modifier = Modifier.height(60.dp))
                Text(text = "Sign Up For Free", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(40.dp))
                CustomTextField(id = R.drawable.icon_profile, placeholder = "Username")
                Spacer(modifier = Modifier.height(12.dp))
                CustomTextField(id = R.drawable.icon_email, placeholder = "Email")
                Spacer(modifier = Modifier.height(12.dp))
                CustomTextField(id = R.drawable.icon_lock, placeholder = "Password")
                Spacer(modifier = Modifier.height(44.dp))
                GradientButton(
                    text = "Create Account",
                    modifier = Modifier
                        .height(56.dp)
                        .width(157.dp),
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextButton(onClick = { }) {
                    Text(text = "Already have an account?")
                }
            }
        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun CustomTextField(id: Int, placeholder: String) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .border(
                width = 1.dp, color = Color(0xFFF4F4F4), shape = RoundedCornerShape(15),
            ),
        value = "",
        onValueChange = { },
        placeholder = { Text(text = placeholder, color = Color(0xFF3B3B3B)) },
        shape = RoundedCornerShape(15),
        leadingIcon = {
            Image(
                modifier = Modifier
                    .height(30.dp),
                painter = painterResource(id = id),
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
        SignUp()
    }
}