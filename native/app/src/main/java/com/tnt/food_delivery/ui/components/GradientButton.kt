package com.tnt.food_delivery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String,
    gradient: Brush = Brush.horizontalGradient(listOf(Color(0xFF53E88B), Color(0xFF15BE77))),
    disabledColor: Color = Color(0xFFF6F6F6),
    onClick: () -> Unit = { },
    radius: Int = 30,
    textStyle: TextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
    isEnable: Boolean = true
) {
    val boxModifier: Modifier = if (isEnable) Modifier
        .background(gradient)
        .then(modifier) else Modifier
        .background(disabledColor)
        .then(modifier)
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = onClick,
        shape = RoundedCornerShape(radius),
        enabled = isEnable,
    ) {
        Box(
            modifier = boxModifier,
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                style = textStyle.copy(
                    color = if (isEnable) Color.White else Color(0xFF3B3B3B).copy(0.3f)
                )
            )
        }
    }
}