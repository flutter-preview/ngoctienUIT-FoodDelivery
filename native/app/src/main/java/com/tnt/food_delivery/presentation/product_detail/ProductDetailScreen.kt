package com.tnt.food_delivery.presentation.product_detail

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.tnt.food_delivery.presentation.restaurant_detail.CustomItem
import com.tnt.food_delivery.presentation.restaurant_detail.components.IconRestaurant
import com.tnt.food_delivery.presentation.restaurant_detail.components.ProductItem
import com.tnt.food_delivery.presentation.restaurant_detail.components.ReviewItem
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun ProductDetailScreen(navController: NavController) {
    Scaffold {
        it
        Box(modifier = Modifier.fillMaxSize())
        {
            Image(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.restaurant_img),
                contentDescription = "restaurant",
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter
            )
            Card(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .padding(top = 18.dp)
                            .width(60.dp)
                            .height(8.dp),
                        shape = RoundedCornerShape(90),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF6ED))
                    ) { }
                }
                LazyColumn {
                    item {
                        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 20.dp)
                            ) {
                                Card(
                                    modifier = Modifier
                                        .height(35.dp)
                                        .width(80.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                    shape = RoundedCornerShape(50)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(
                                                Brush.horizontalGradient(
                                                    listOf(
                                                        Color(0xFF53E88B).copy(0.1f),
                                                        Color(0xFF15BE77).copy(0.1f)
                                                    )
                                                )
                                            )
                                    ) {
                                        Text(
                                            modifier = Modifier.align(Alignment.Center),
                                            text = "Popular",
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight(600),
                                            style = TextStyle(
                                                brush = Brush.linearGradient(
                                                    colors = listOf(
                                                        Color(0xFF53E88B),
                                                        Color(0xFF15BE77)
                                                    )
                                                )
                                            )
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                IconRestaurant(
                                    onClick = { /*TODO*/ },
                                    id = R.drawable.icon_location
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                IconRestaurant(
                                    onClick = { /*TODO*/ },
                                    id = R.drawable.icon_favourite
                                )
                            }
                            Text(
                                text = "Wijie Bar and Resto",
                                fontSize = 27.sp,
                                color = Color(0xFF09051C),
                                fontWeight = FontWeight.Bold
                            )
                            Row(modifier = Modifier.padding(vertical = 20.dp)) {
                                CustomItem(text = "4.8 rating", id = R.drawable.icon_star)
                                Spacer(modifier = Modifier.width(20.dp))
                                CustomItem(text = "2000+ Order", id = R.drawable.icon_shopping_bag)
                            }
                            Text(
                                text = "Nulla occaecat velit laborum exercitation ullamco. Elit labore eu aute elit nostrud culpa velit excepteur deserunt sunt. Velit non est cillum consequat cupidatat ex Lorem laboris labore aliqua ad duis eu laborum.\n" +
                                        "\n" +
                                        "Strowberry\n" +
                                        "Cream\n" +
                                        "wheat\n" +
                                        "\n" +
                                        "Nulla occaecat velit laborum exercitation ullamco. Elit labore eu aute elit nostrud culpa velit excepteur deserunt sunt.",
                                fontSize = 12.sp,
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            modifier = Modifier.padding(start = 30.dp),
                            text = "Customer reviews",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF09051C)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    items(10) { index ->
                        ReviewItem()
                    }
                    item {
                        Spacer(modifier = Modifier.height(70.dp))
                    }
                }
            }
//            BackButton {
//
//            }
            GradientButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 15.dp)
                    .height(57.dp),
                text = "Add To Cart",
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantDetailPreview() {
    FoodDeliveryTheme {
        ProductDetailScreen(rememberNavController())
    }
}