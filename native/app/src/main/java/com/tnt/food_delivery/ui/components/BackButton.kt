package com.tnt.food_delivery.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.tnt.food_delivery.R

@Composable
fun BackButton(
    modifier: Modifier = Modifier.padding(top = 38.dp, bottom = 20.dp),
    backgroundColor: Color = Color(0xFFF9A84D).copy(alpha = 0.1f),
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(15),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .width(45.dp)
                .height(45.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrow_back),
                contentDescription = "Arrow Back Icon",
                tint = Color(0xFFDA6317)
            )
        }
    }
}