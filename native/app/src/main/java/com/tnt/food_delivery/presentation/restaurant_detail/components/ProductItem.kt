package com.tnt.food_delivery.presentation.restaurant_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.food_delivery.R
import com.tnt.food_delivery.presentation.sign_in.components.shadow

@Composable
fun ProductItem(isStart: Boolean) {
    Card(
        modifier = Modifier
            .padding(start = if (isStart) 20.dp else 0.dp, end = 20.dp)
            .width(150.dp)
            .height(170.dp)
            .shadow(
                color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                spread = 25.dp,
                blurRadius = 50.dp,
                offsetX = 12.dp,
                offsetY = 26.dp,
            ),
        shape = RoundedCornerShape(22),
        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.elevatedCardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                painter = painterResource(id = R.drawable.icon_piza),
                contentDescription = "piza"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Spacy fresh crab",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF09051C)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "12 $",
                fontSize = 13.sp,
                color = Color.Black.copy(0.5f)
            )
        }
    }
}