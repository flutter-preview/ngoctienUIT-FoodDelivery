package com.tnt.food_delivery.presentation.restaurant_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.food_delivery.R
import com.tnt.food_delivery.presentation.sign_in.components.shadow

@Composable
fun ReviewItem() {
    Card(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .height(130.dp)
            .padding(horizontal = 30.dp)
            .shadow(
                color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                spread = 25.dp,
                blurRadius = 50.dp,
            ),
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp)
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
                    .clip(RoundedCornerShape(20)),
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "tnt",
                contentScale = ContentScale.FillBounds,
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Row {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Dianne Russell",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF09051C)
                        )
                        Text(
                            text = "12 April 2021",
                            fontSize = 12.sp,
                            color = Color(0xFF3B3B3B).copy(0.3f),
                            letterSpacing = 0.5.sp
                        )
                    }
                    ItemStar()
                    Spacer(modifier = Modifier.width(20.dp))
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "This Is great, So delicious! You Must Here, With Your family . . ",
                    fontSize = 12.sp,
                )
            }
        }
    }
}