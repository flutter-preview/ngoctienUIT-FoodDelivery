package com.tnt.food_delivery.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tnt.food_delivery.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val Viga = FontFamily(Font(R.font.viga_regular))

val Inter = FontFamily(
    Font(R.font.inter_thin, weight = FontWeight(100)),
    Font(R.font.inter_extralight, weight = FontWeight(200)),
    Font(R.font.inter_light, weight = FontWeight(300)),
    Font(R.font.inter_regular, weight = FontWeight(400)),
    Font(R.font.inter_medium, weight = FontWeight(500)),
    Font(R.font.inter_semibold, weight = FontWeight(600)),
    Font(R.font.inter_bold, weight = FontWeight(700)),
    Font(R.font.inter_extrabold, weight = FontWeight(800)),
    Font(R.font.inter_black, weight = FontWeight(900)),
)