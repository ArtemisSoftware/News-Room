package com.news.presentation.news.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.news.presentation.R

@Composable
fun UserTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(text = "Welcome back,", fontSize = 16.sp, color = Color.Gray)
            Text(text = "Carolina Terner", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Box {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "This is a profile picture",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(50.dp)),
            )

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(border = BorderStroke(3.dp, color = Color.Black))
                    .background(Color.Black)
                    .align(alignment = Alignment.BottomStart),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "2",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(3.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserTopBarPreview() {
    UserTopBar()
}
