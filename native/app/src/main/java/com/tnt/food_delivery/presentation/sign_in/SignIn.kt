package com.tnt.food_delivery.presentation.sign_in

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.food_delivery.R
import com.tnt.food_delivery.presentation.onboarding.components.GradientButton
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import com.tnt.food_delivery.ui.theme.Inter
import java.nio.file.WatchEvent

@ExperimentalTextApi
@ExperimentalMaterial3Api
@Composable
fun SignIn() {
//    var text by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold {
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
                Text(text = "Login To Your Account", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(40.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .border(
                            width = 1.dp, color = Color(0xFFF4F4F4), shape = RoundedCornerShape(15),
                        ),
                    value = "",
                    onValueChange = { },
                    placeholder = { Text(text = "Email") },
                    shape = RoundedCornerShape(15),
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .border(
                            width = 1.dp, color = Color(0xFFF4F4F4), shape = RoundedCornerShape(15),
                        ),
                    value = "",
                    onValueChange = { },
                    placeholder = { Text(text = "Password") },
                    shape = RoundedCornerShape(15),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Or Continue With", fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
                    Text(text = "Forgot Your Password?")
                }
                Spacer(modifier = Modifier.height(36.dp))
                GradientButton(
                    text = "Login",
                    modifier = Modifier
                        .height(56.dp)
                        .width(157.dp),
                )
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
                width = 2.dp, color = Color(0xFFF4F4F4), shape = RoundedCornerShape(15),
            ),
    ) {
        Image(
            modifier = Modifier.height(35.dp),
            painter = painterResource(id = id),
            contentDescription = "tnt"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text)
    }
}

@ExperimentalTextApi
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    FoodDeliveryTheme {
        SignIn()
    }
}