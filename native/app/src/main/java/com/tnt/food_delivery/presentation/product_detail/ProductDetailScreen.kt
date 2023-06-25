package com.tnt.food_delivery.presentation.product_detail

import android.os.Build
import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import com.tnt.food_delivery.R
import com.tnt.food_delivery.data.nav_type.ProductParcelable
import com.tnt.food_delivery.data.response.ProductResponse
import com.tnt.food_delivery.presentation.onboarding.components.GradientButton
import com.tnt.food_delivery.presentation.payment_method.components.BackButton
import com.tnt.food_delivery.presentation.restaurant_detail.CustomItem
import com.tnt.food_delivery.presentation.restaurant_detail.components.IconRestaurant
import com.tnt.food_delivery.presentation.restaurant_detail.components.ProductItem
import com.tnt.food_delivery.presentation.restaurant_detail.components.ReviewItem
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

class ProductNavType : NavType<ProductParcelable>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ProductParcelable? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, ProductParcelable::class.java)
        } else {
            @Suppress("DEPRECATION") bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): ProductParcelable {
        return Gson().fromJson(value, ProductParcelable::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ProductParcelable) {
        bundle.putParcelable(key, value)
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ProductDetailScreen(navController: NavController, product: ProductParcelable) {
    val state = rememberCollapsingToolbarScaffoldState()
    var visible by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.image)
                .crossfade(true)
                .build(),
            loading = { CircularProgressIndicator() },
            contentDescription = "image",
            contentScale = ContentScale.FillBounds,
        )
//        Image(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.6f),
//            painter = painterResource(id = R.drawable.restaurant_img),
//            contentDescription = "restaurant",
//            contentScale = ContentScale.FillBounds,
//            alignment = Alignment.TopCenter
//        )
        CollapsingToolbarScaffold(
            modifier = Modifier,
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                visible = state.toolbarState.progress == 0.0f

                Box(
                    modifier = Modifier
                        .background(if (state.toolbarState.progress == 0.0f) Color.White else Color.Transparent)
                        .fillMaxWidth()
                        .height((configuration.screenHeightDp * 0.4).dp)
                        .pin()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(57.dp)
                        .road(Alignment.TopCenter, Alignment.TopCenter),
                ) {
                    AnimatedVisibility(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 55.dp),
                        visible = visible,
                        enter = slideInVertically {
                            with(density) { -40.dp.roundToPx() }
                        } + expandVertically(expandFrom = Alignment.Top) + fadeIn(initialAlpha = 0.3f),
                        exit = slideOutVertically() + shrinkVertically() + fadeOut()
                    ) {
                        Text(
                            text = "Rainbow Sandwich Sugarless",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF09051C),
                            fontSize = 20.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    BackButton(
                        modifier = Modifier
                            .padding(0.dp)
                            .align(Alignment.CenterStart)
                            .padding(start = 5.dp),
                        backgroundColor = if (state.toolbarState.progress == 0.0f) Color.Transparent
                        else Color.White.copy(0.55f)
                    ) {
                        navController.popBackStack()
                    }
                }
            }
        ) {
            LazyColumn {
                item {
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(
                            topStart = if (state.toolbarState.progress == 0.0f) 0.dp else 35.dp,
                            topEnd = if (state.toolbarState.progress == 0.0f) 0.dp else 35.dp
                        ),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        if (state.toolbarState.progress != 0.0f)
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
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFFFEF6ED)
                                    )
                                ) { }
                            }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(0.6f)
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(horizontal = 30.dp)
                        ) {
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
                                text = "Rainbow Sandwich Sugarless",
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
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Recommend",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                TextButton(onClick = { }) {
                                    Text(
                                        text = "View All",
                                        fontSize = 12.sp,
                                        color = Color(0xFFFF7C32)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        LazyRow {
                            items(10) { index ->
                                ProductItem(index == 0)
                            }
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
                }
                items(10) { index ->
                    ReviewItem()
                }
            }
        }
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

@Preview(showBackground = true)
@Composable
fun RestaurantDetailPreview() {
    FoodDeliveryTheme {
        ProductDetailScreen(rememberNavController(), ProductParcelable())
    }
}