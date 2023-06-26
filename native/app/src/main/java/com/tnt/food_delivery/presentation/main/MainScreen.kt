package com.tnt.food_delivery.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.presentation.filter.FilterScreen
import com.tnt.food_delivery.presentation.home.HomeScreen
import com.tnt.food_delivery.presentation.message.MessageScreen
import com.tnt.food_delivery.presentation.notification.NotificationScreen
import com.tnt.food_delivery.presentation.profile.ProfileScreen
import com.tnt.food_delivery.presentation.upload_photo.UploadPhotoScreen
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import kotlinx.coroutines.launch

val tabs = listOf<Map<String, Any>>(
    mapOf("icon" to R.drawable.icon_restaurant, "title" to "Food"),
    mapOf("icon" to R.drawable.icon_chat_disable, "title" to "Chat"),
    mapOf("icon" to R.drawable.icon_home_disable, "title" to "Home"),
    mapOf("icon" to R.drawable.icon_no_notifiaction, "title" to "Notification"),
    mapOf("icon" to R.drawable.icon_profile_disable, "title" to "Profile"),
    )

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { index ->
            currentIndex = index;
        }
    }

    Scaffold {
        it
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HorizontalPager(
                pageCount = 5,
                modifier = Modifier.fillMaxSize(),
                state = pagerState,
            ) { index ->
                when (index) {
                    0 -> HomeScreen(navController)
                    1 -> MessageScreen(navController)
                    2 -> UploadPhotoScreen()
                    3 -> NotificationScreen()
                    4 -> ProfileScreen(navController)
                }
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(30),
                modifier = Modifier
                    .padding(all = 15.dp)
                    .fillMaxWidth()
                    .height(75.dp)
                    .align(Alignment.BottomCenter),
                elevation = CardDefaults.cardElevation(3.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp)
                ) {
                    for (index in 0..4) {
                        TabBarItem(index = index, currentIndex = currentIndex) {
                            if (currentIndex != index) {
                                currentIndex = index
                                scope.launch {
                                    pagerState.animateScrollToPage(page = index)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TabBarItem(index: Int, currentIndex: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(30),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = if (currentIndex == index) Modifier
                .fillMaxHeight()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFF53E88B).copy(alpha = 0.1f),
                            Color(0xFF15BE77).copy(alpha = 0.1f)
                        )
                    )
                )
                .padding(
                    vertical = 10.dp,
                    horizontal = 20.dp
                ) else Modifier.padding(all = 10.dp)

        ) {
            Image(
                painter = painterResource(id = tabs[index]["icon"] as Int),
                contentDescription = "icon",
                modifier = Modifier
                    .height(25.dp)
                    .width(25.dp)
            )
            if (currentIndex == index)
                Spacer(modifier = Modifier.width(5.dp))
            if (currentIndex == index)
                Text(
                    text = tabs[index]["title"] as String,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    FoodDeliveryTheme {
        MainScreen(rememberNavController())
    }
}