@file:OptIn(ExperimentalFoundationApi::class)

package com.tnt.food_delivery.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.food_delivery.R
import com.tnt.food_delivery.presentation.onboarding.components.GradientButton
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun OnboardingScreen() {
    val list = listOf<Map<String, Any>>(
        mapOf(
            "image" to R.drawable.onboarding_light_1,
            "title" to "Find your  Comfort Food here",
            "content" to "Here You Can find a chef or dish for every taste and color. Enjoy!"
        ), mapOf(
            "image" to R.drawable.onboarding_light_2,
            "title" to "Food TNT is Where Your Comfort Food Lives",
            "content" to "Enjoy a fast and smooth food delivery at your doorstep"
        )
    )

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold()
    {
        it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize(),
        ) {
            HorizontalPager(pageCount = 2, userScrollEnabled = false, state = pagerState)
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Image(
                        painter = painterResource(id = list[it]["image"] as Int),
                        contentDescription = "test",
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Text(
                        list[it]["title"] as String,
                        fontSize = 22.sp,
                        maxLines = 2,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 60.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        list[it]["content"] as String,
                        maxLines = 2,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 65.dp),
                    )
                }
            }
            GradientButton(
                onClick = {
                    if (pagerState.currentPage == 0) {
                        scope.launch {
                            pagerState.animateScrollToPage(page = 1)
                        }
                    } else {
//                        scope.launch {
//                            pagerState.animateScrollToPage(page = 0)
//                        }
                    }
                },
                text = "Next",
                modifier = Modifier
                    .height(56.dp)
                    .width(157.dp)

            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    FoodDeliveryTheme {
        OnboardingScreen()
    }
}