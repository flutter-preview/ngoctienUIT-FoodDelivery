package com.tnt.food_delivery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.food_delivery.R
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@Composable
fun PostItem() {
    val configuration = LocalConfiguration.current

    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.height(55.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(90)),
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "restaurant",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.TopCenter
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.height(55.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Trần Ngọc Tiến",
                    fontSize = 14.sp,
                    color = Color(0xFF09051C),
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Vừa xong",
                    fontSize = 14.sp,
                    color = Color(0xFF3B3B3B).copy(0.3f),
                    letterSpacing = 0.5.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .size(25.dp)
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                Brush.horizontalGradient(
                                    listOf(Color(0xFF53E88B), Color(0xFF15BE77)),
                                ), blendMode = BlendMode.SrcAtop
                            )
                        }
                    }
                    .clickable { },
                painter = painterResource(R.drawable.icon_more_vert),
                contentDescription = "setting"
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "Trần Ngọc Tiến",
            fontSize = 14.sp,
            color = Color(0xFF09051C),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            modifier = Modifier.size(configuration.screenWidthDp.dp),
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "tnt",
            contentScale = ContentScale.FillBounds,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(30.dp).clickable {  },
                    painter = painterResource(id = R.drawable.icon_favourite),
                    contentDescription = "tnt",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "0",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF09051C),
                )
            }
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(30.dp).clickable {  },
                    painter = painterResource(id = R.drawable.icon_comment),
                    contentDescription = "tnt",
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "0",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF09051C),
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PostItemPreview() {
    FoodDeliveryTheme {
        PostItem()
    }
}