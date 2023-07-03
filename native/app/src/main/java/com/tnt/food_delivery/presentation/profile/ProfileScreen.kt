package com.tnt.food_delivery.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.ui.components.PostItem
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun ProfileScreen(navController: NavController) {
    val state = rememberCollapsingToolbarScaffoldState()
    var visible by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            val progress = state.toolbarState.progress
            visible = progress == 0.0f
            val size =
                if (configuration.screenWidthDp * 0.5 * progress > 50) configuration.screenWidthDp * 0.5f * progress else 50f
            val fontSize = (20 + 7 * progress).sp

            Box(
                modifier = Modifier
                    .background(if (progress == 0.0f) Color.White else Color.Transparent)
                    .fillMaxWidth()
                    .height(configuration.screenWidthDp.dp)
                    .pin()
            ) {
                if (!visible)
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(5.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded),
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "restaurant",
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.TopCenter
                    )
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(30.dp)
                        .align(Alignment.TopEnd)
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
                    painter = painterResource(R.drawable.icon_menu),
                    contentDescription = "setting"
                )
            }

            Image(
                modifier = Modifier
                    .padding(all = 5.dp)
                    .size(size.dp)
                    .clip(RoundedCornerShape(90))
                    .border(
                        width = (7 * progress).dp,
                        shape = RoundedCornerShape(90),
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFF53E88B), Color(0xFF15BE77)),
                        ),
                    )
                    .road(whenCollapsed = Alignment.CenterStart, whenExpanded = Alignment.Center),
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "restaurant",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.TopCenter
            )

            Card(
                modifier = Modifier
                    .padding(bottom = (20 * progress).dp)
                    .road(whenCollapsed = Alignment.Center, whenExpanded = Alignment.BottomCenter)
                    .height(57.dp)
                    .padding(
                        vertical = (5 + 10 * (1 - progress)).dp,
                        horizontal = (55 * (1 - progress)).dp
                    ),
                colors = CardDefaults.cardColors(Color(0xFFFEAD1D).copy(0.5f * progress)),
                shape = RoundedCornerShape(15)
            ) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = (15 * progress).dp,
                        vertical = (5 * progress).dp
                    ),
                    text = "Trần Ngọc Tiến",
                    fontSize = fontSize,
                    color = Color(0xFF09051C),
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    ) {
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "@ngoctienTNT",
                        fontSize = 14.sp,
                        color = Color(0xFF3B3B3B).copy(0.3f),
                        letterSpacing = 0.5.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier.width(100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "0")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Follower", fontWeight = FontWeight.Bold)
                    }
                    Column(
                        modifier = Modifier.width(100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "0")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Following", fontWeight = FontWeight.Bold)
                    }
                    Column(
                        modifier = Modifier.width(100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "0")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Post", fontWeight = FontWeight.Bold)
                    }
                }
            }
            items(10) { index ->
                PostItem()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    FoodDeliveryTheme {
        ProfileScreen(rememberNavController())
    }
}