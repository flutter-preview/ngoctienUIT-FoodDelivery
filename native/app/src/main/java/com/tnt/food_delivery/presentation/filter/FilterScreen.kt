package com.tnt.food_delivery.presentation.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.ui.components.GradientButton
import com.tnt.food_delivery.ui.components.shadow
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterScreen(navController: NavController) {
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
                Row(
                    modifier = Modifier
                        .padding(top = 60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        modifier = Modifier
                            .padding(end = 60.dp)
                            .fillMaxWidth(fraction = 0.75f),
                        text = "Find Your Favorite Food",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 40.sp,
                        maxLines = 2,
                    )
                    Card(
                        modifier = Modifier
                            .height(45.dp)
                            .width(45.dp)
                            .shadow(
                                color = Color(0xFF144E5A).copy(alpha = 0.2f),
                                spread = 15.dp,
                                blurRadius = 50.dp,
                                offsetX = 11.dp,
                                offsetY = 28.dp,
                            ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(30)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
                        {
                            Image(
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(25.dp),
                                painter = painterResource(id = R.drawable.icon_notifiaction),
                                contentDescription = "tnt"
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Type", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    CustomItemFilter(text = "Restaurant")
                    CustomItemFilter(text = "Menu")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Location", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    CustomItemFilter(text = "1 Km")
                    CustomItemFilter(text = ">10 Km")
                    CustomItemFilter(text = "<10 Km")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Food", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow {
                    CustomItemFilter(text = "Cake")
                    CustomItemFilter(text = "Soup")
                    CustomItemFilter(text = "Main Course")
                    CustomItemFilter(text = "Appetizer")
                    CustomItemFilter(text = "Dessert")
                }
                Spacer(modifier = Modifier.height(50.dp))
                GradientButton(
                    text = "Search",
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun CustomItemFilter(text: String) {
    Card(
        modifier = Modifier.padding(end = 10.dp, top = 10.dp, bottom = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFEAD1D).copy(alpha = 0.1f)),
        shape = RoundedCornerShape(30)
    ) {
        Box(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = Color(0xFFDA6317))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterScreenPreview() {
    FoodDeliveryTheme {
        FilterScreen(rememberNavController())
    }
}