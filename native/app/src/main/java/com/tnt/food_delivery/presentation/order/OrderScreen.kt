package com.tnt.food_delivery.presentation.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.presentation.payment_method.components.BackButton
import com.tnt.food_delivery.presentation.sign_in.components.shadow
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun OrderScreen(navController: NavController) {
    Scaffold {
        it
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.background_order),
                contentDescription = "tnt"
            )
            LazyColumn(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp)
            ) {
                item {
                    BackButton {
                        navController.popBackStack()
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Order details",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF09051C)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                items(6) {
                    Column {
                        ItemOrder()
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                            .clip(RoundedCornerShape(12))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(
                                        Color(0xFF53E88B),
                                        Color(0xFF15BE77)
                                    )
                                )
                            )
                    ) {
                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painterResource(id = R.drawable.background_price_info),
                            contentDescription = "tnt"
                        )
                        Column {
                            Spacer(modifier = Modifier.height(20.dp))
                            PriceItem(title = "Sub-Total", price = "120 \$")
                            Spacer(modifier = Modifier.height(5.dp))
                            PriceItem(title = "Delivery Charge", price = "10 \$")
                            Spacer(modifier = Modifier.height(5.dp))
                            PriceItem(title = "Discount", price = "20 \$")
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(modifier = Modifier.padding(start = 30.dp, end = 20.dp)) {
                                Text(
                                    text = "Total",
                                    fontSize = 18.sp,
                                    color = Color(0xFFFEFEFF),
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = "150\$",
                                    fontSize = 18.sp,
                                    color = Color(0xFFFEFEFF),
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(57.dp)
                                    .padding(horizontal = 10.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFFFEFEFF)),
                                onClick = {},
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Text(
                                    text = "Place My Order",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp,
                                    style = TextStyle(
                                        brush = Brush.linearGradient(
                                            colors = listOf(Color(0xFF53E88B), Color(0xFF15BE77))
                                        )
                                    )
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ItemOrder() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
            .shadow(
                color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                spread = 25.dp,
                blurRadius = 50.dp,
                offsetX = 12.dp,
                offsetY = 26.dp,
            ),
        shape = RoundedCornerShape(22),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                modifier = Modifier
                    .height(65.dp)
                    .width(65.dp)
                    .clip(RoundedCornerShape(20)),
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "tnt",
                contentScale = ContentScale.FillBounds,
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Spacy fresh crab",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF09051C)
                )
                Text(
                    text = "Waroenk kita",
                    fontSize = 14.sp,
                    color = Color(0xFF3B3B3B).copy(0.3f),
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "$ 35",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
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
            ItemQuantity(
                id = R.drawable.icon_remove,
                alpha = 0.3f,
                onClick = {}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "1",
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF181818),
                letterSpacing = 0.57.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            ItemQuantity(
                id = R.drawable.icon_add,
                alpha = 1f,
                onClick = {}
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun ItemQuantity(id: Int, alpha: Float, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(22))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFF53E88B).copy(alpha),
                        Color(0xFF15BE77).copy(alpha)
                    )
                )
            )
            .clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier
                .padding(5.dp)
                .size(18.dp),
            imageVector = ImageVector.vectorResource(id = id),
            contentDescription = "Arrow Back Icon",
            tint = Color.White
        )
    }
}

@Composable
fun PriceItem(title: String, price: String) {
    Row(modifier = Modifier.padding(start = 30.dp, end = 20.dp)) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color(0xFFFEFEFF),
            fontWeight = FontWeight(600),
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = price,
            fontSize = 14.sp,
            color = Color(0xFFFEFEFF),
            fontWeight = FontWeight(600),
            letterSpacing = 0.5.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderPreview() {
    FoodDeliveryTheme {
        OrderScreen(rememberNavController())
    }
}