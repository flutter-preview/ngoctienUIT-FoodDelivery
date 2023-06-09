package com.tnt.food_delivery.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import com.tnt.food_delivery.R
import com.tnt.food_delivery.presentation.home.HomeScreen
import com.tnt.food_delivery.presentation.payment_method.PaymentMethodScreen
import com.tnt.food_delivery.presentation.sign_in.SignInScreen
import com.tnt.food_delivery.presentation.sign_up.SignUpScreen
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalTextApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val screen = listOf(
        HomeScreen(),
        SignInScreen(),
        SignUpScreen(),
        PaymentMethodScreen(),
    )

    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = Color.White,
            contentColor = Color.Black,
            currentIndex = 0,
            function = { index ->
                scope.launch {
                    pagerState.animateScrollToPage(page = index)
                }
            }
        )
    }) {
        it
        HorizontalPager(pageCount = 4, state = pagerState) { index ->
            screen[index]
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    FoodDeliveryTheme {
        MainScreen()
    }
}

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : BottomNavItem("Home", R.drawable.icon_home_disable, "home")
    object Profile : BottomNavItem("Profile", R.drawable.icon_profile_disable, "profile")
    object Buy : BottomNavItem("Buy", R.drawable.icon_buy_disable, "buy")
    object Chat : BottomNavItem("Chat", R.drawable.icon_chat_disable, "chat")
}

@Composable
fun BottomNavigation(
    currentIndex: Int,
    backgroundColor: Color,
    contentColor: Color,
    function: (index: Int) -> Unit
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Buy,
        BottomNavItem.Chat,
    )
    NavigationBar(
        contentColor = contentColor,
        containerColor = backgroundColor
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(selected = index == currentIndex, onClick = {
                function(index)
            }, icon = {
                Image(painter = painterResource(id = item.icon), contentDescription = "")
            }, label = { Text(text = item.title) })
        }
    }
}
