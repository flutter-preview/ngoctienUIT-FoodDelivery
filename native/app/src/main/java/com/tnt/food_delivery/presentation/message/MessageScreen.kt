package com.tnt.food_delivery.presentation.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.core.utils.NavDestinations
import com.tnt.food_delivery.presentation.home.HomeScreen
import com.tnt.food_delivery.presentation.payment_method.components.BackButton
import com.tnt.food_delivery.presentation.sign_in.components.shadow
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreen(navController: NavController) {
    Scaffold {
        it
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.triangle_home_background),
                contentDescription = "tnt"
            )
            Column(
                modifier = Modifier.padding(horizontal = 25.dp),
            ) {
                BackButton {

                }
                Text(text = "Chat", fontSize = 25.sp, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(15.dp))
                ItemMessage(
                    id = R.drawable.avatar,
                    title = "ngoctienTNT",
                    time = "20:00",
                    message = "Hello"
                )
                ItemMessage(
                    id = R.drawable.avatar,
                    title = "ngoctienTNT",
                    time = "20:00",
                    message = "Hello"
                )
                ItemMessage(
                    id = R.drawable.avatar,
                    title = "ngoctienTNT",
                    time = "20:00",
                    message = "Hello"
                )
            }
        }
    }
}

@Composable
fun ItemMessage(id: Int, title: String, time: String, message: String) {
    Card(
        modifier = Modifier
            .padding(top = 15.dp)
            .height(80.dp)
            .fillMaxWidth()
            .shadow(
                color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                spread = 25.dp,
                blurRadius = 50.dp,
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(25)
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .clip(RoundedCornerShape(20)),
                painter = painterResource(id = id),
                contentDescription = "tnt",
                contentScale = ContentScale.FillBounds,
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(all = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = time,
                        color = Color(0xFF3B3B3B).copy(alpha = 0.3f),
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = message,
                    color = Color(0xFF3B3B3B).copy(alpha = 0.3f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagePreview() {
    FoodDeliveryTheme {
        MessageScreen(rememberNavController())
    }
}