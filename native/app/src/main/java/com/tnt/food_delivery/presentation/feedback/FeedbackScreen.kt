package com.tnt.food_delivery.presentation.feedback

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.presentation.onboarding.components.GradientButton
import com.tnt.food_delivery.presentation.sign_up.CustomTextField
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun FeedbackScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    var feedback by rememberSaveable { mutableStateOf("") }
    
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
                Spacer(modifier = Modifier.height((configuration.screenWidthDp * 0.4).dp))
                Image(
                    painter = painterResource(id = R.drawable.restaurant_img),
                    contentDescription = "test",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size((configuration.screenWidthDp * 0.5).dp)
                        .clip(RoundedCornerShape(90))
                        .border(
                            width = 7.dp,
                            shape = RoundedCornerShape(90),
                            brush = Brush.horizontalGradient(
                                listOf(Color(0xFF53E88B), Color(0xFF15BE77)),
                            ),
                        )
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Thank You!\nEnjoy Your Meal",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF09051C),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Please rate your Food",
                    fontSize = 14.sp,
                    color = Color(0xFF3B3B3B).copy(0.3f),
                    letterSpacing = 0.5.sp
                )
                RateStar(
                    index = 2,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { }
                )
                Spacer(modifier = Modifier.height(50.dp))
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .border(
                            width = 1.dp, color = Color(0xFFE8E8E8), shape = RoundedCornerShape(30),
                        ),
                    icon = R.drawable.icon_edit,
                    value = feedback,
                    onValueChange = { value -> feedback = value },
                    placeholder = "Leave feedback"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(20.dp))
                    GradientButton(
                        modifier = Modifier
                            .height(56.dp)
                            .weight(4f),
                        text = "Submit"
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    TextButton(
                        modifier = Modifier
                            .height(56.dp)
                            .weight(1f),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = "Skip",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFF53E88B), Color(0xFF15BE77))
                                )
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }
}

@Composable
fun RateStar(index: Int, modifier: Modifier, onClick: (index: Int) -> Unit) {
    Row(
        modifier = modifier
            .height(100.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5)
        {
            Icon(
                modifier = Modifier.clickable { onClick(it) },
                painter = painterResource(id = if (index == it) R.drawable.pick_rate_star else R.drawable.rate_star),
                contentDescription = "rate star",
                tint = if (it <= index) Color(0xFFFEAD1D) else Color(0xFFFEAD1D).copy(0.3f)
            )
        }
    }
}

@ExperimentalTextApi
@Preview(showBackground = true)
@Composable
fun FeedbackPreview() {
    FoodDeliveryTheme {
        FeedbackScreen(rememberNavController())
    }
}