package com.tnt.food_delivery.presentation.restaurant_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconRestaurant(onClick: () -> Unit, id: Int) {
    Card(
        modifier = Modifier
            .height(35.dp)
            .width(35.dp)
            .clickable { onClick() },
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
            Image(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .align(Alignment.Center),
                painter = painterResource(id),
                contentDescription = "location",
                contentScale = ContentScale.Fit
            )
        }
    }
}