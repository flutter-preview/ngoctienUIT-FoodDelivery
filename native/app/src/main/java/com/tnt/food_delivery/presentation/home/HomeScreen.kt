package com.tnt.food_delivery.presentation.home

import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import com.tnt.food_delivery.R
import com.tnt.food_delivery.core.utils.EventResults
import com.tnt.food_delivery.core.utils.EventStatus
import com.tnt.food_delivery.core.utils.NavDestinations
import com.tnt.food_delivery.data.response.ProductResponse
import com.tnt.food_delivery.data.response.UserResponse
import com.tnt.food_delivery.presentation.sign_in.components.shadow
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val restaurants by viewModel.restaurant.observeAsState()
    val products by viewModel.product.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllRestaurant()
        viewModel.getAllProduct()
    }

    Scaffold {
        it
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.triangle_home_background),
                contentDescription = "tnt"
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 25.dp)
                    .verticalScroll(rememberScrollState()),
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
                Row {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = "",
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color(
                                0xFFF9A84D
                            ).copy(alpha = 0.1f),
                            disabledBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                        ),
                        onValueChange = { },
                        placeholder = {
                            Text(
                                text = "What do you want to order?",
                                color = Color(0xFFDA6317).copy(alpha = 0.4f),
                                fontSize = 12.sp,
                                maxLines = 1,
                            )
                        },
                        shape = RoundedCornerShape(30),
                        leadingIcon = {
                            Image(
                                modifier = Modifier
                                    .height(30.dp),
                                painter = painterResource(id = R.drawable.icon_search),
                                contentDescription = "icon profile",
                            )
                        },

                        )
                    Spacer(modifier = Modifier.width(10.dp))
                    Card(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .clickable {
                                navController.navigate(NavDestinations.FILTER_SCREEN) {
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) { saveState = true }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF9A84D).copy(alpha = 0.1f)
                        ),
                        shape = RoundedCornerShape(30)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(20.dp),
                                painter = painterResource(id = R.drawable.icon_filter),
                                contentDescription = "tnt"
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(15)
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.promo_advertising),
                        contentDescription = "tnt",
                        contentScale = ContentScale.FillWidth
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Nearest Restaurant",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { }) {
                        Text(text = "View More", fontSize = 12.sp, color = Color(0xFFFF7C32))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ShowListRestaurant(restaurants)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Popular Menu",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { }) {
                        Text(text = "View More", fontSize = 12.sp, color = Color(0xFFFF7C32))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ShowListProduct(navController, products)
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun ShowListRestaurant(restaurants: EventResults<List<UserResponse>>?) {
    if (restaurants != null
        && restaurants.status == EventStatus.SUCCESS
        && restaurants.data!!.isNotEmpty()
    ) {
        LazyRow {
            items(restaurants.data.size) { index ->
                Log.d("item index", index.toString())
                Log.d("item", restaurants.data[index].toString())
                Row {
                    Card(
                        modifier = Modifier
                            .height(180.dp)
                            .width(150.dp)
                            .shadow(
                                color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                                spread = 25.dp,
                                blurRadius = 60.dp,
                                offsetX = 10.dp,
                                offsetY = 20.dp,
                            ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(15)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.tempt_image),
                                contentDescription = "tnt"
                            )
                            Text(
                                text = restaurants.data[index].name!!,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = restaurants.data[index].email!!,
                                fontSize = 13.sp,
                                color = Color(0xFF3B3B3B).copy(alpha = 0.3f),
                            )
                        }
                    }
                    if (index != restaurants.data.size) Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }
}

@Composable
fun ShowListProduct(navController: NavController, products: EventResults<List<ProductResponse>>?) {
    if (products != null
        && products.status == EventStatus.SUCCESS
        && products.data!!.isNotEmpty()
    ) {
        Column {
            repeat(products.data.size)
            { index ->
                Log.d("item index", index.toString())
                Log.d("item", products.data[index].toString())
                Column {
                    Card(
                        modifier = Modifier
                            .height(90.dp)
                            .fillMaxWidth()
                            .clickable {
//                                val product = ProductParcelable.fromProductResponse(products.data[index])
                                navController.navigate("${NavDestinations.PRODUCT_DETAIL_SCREEN}/${products.data[index]}") {
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) { saveState = true }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            .shadow(
                                color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                                spread = 25.dp,
                                blurRadius = 60.dp,
                                offsetX = 10.dp,
                                offsetY = 20.dp,
                            ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(15)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .height(65.dp)
                                    .width(65.dp)
                                    .clip(RoundedCornerShape(20)),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(products.data[index].image)
                                    .crossfade(true)
                                    .build(),
                                loading = { CircularProgressIndicator() },
                                contentDescription = "image",
                                contentScale = ContentScale.FillBounds,
                            )
//                            Image(
//                                modifier = Modifier
//                                    .height(65.dp)
//                                    .width(65.dp)
//                                    .clip(RoundedCornerShape(20)),
//                                painter = painterResource(id = R.drawable.avatar),
//                                contentDescription = "tnt"
//                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = products.data[index].name!!,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = products.data[index].description!!,
                                    fontSize = 14.sp,
                                    color = Color(0xFF3B3B3B).copy(alpha = 0.3f),
                                )
                            }
                            Text(
                                text = products.data[index].price.toString(),
                                color = Color(0xFFFEAD1D),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    if (index != products.data.size) Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FoodDeliveryTheme {
        HomeScreen(rememberNavController())
    }
}