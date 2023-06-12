package com.news.presentation.news.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
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
import com.news.domain.models.Article
import com.news.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleResumeCard(article: Article, isLast: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Left Section
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = article.title.capitalizeWords(),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier,
                ) {
                    CustomImageChip(
                       text = article.author,
                        imageId = R.drawable.profile,
                        selected = true,
                    )

                    Text(text = ".", color = Color.Gray, fontSize = 15.sp)
                    Text(text = "3 mins ago", color = Color.Gray, fontSize = 15.sp)
                }
            }

            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(id = R.drawable.profile),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }

        if (isLast) {
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
            )
        }
    }
}

fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

@Composable
private fun CustomImageChip(
    text: String,
    imageId: Int,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    // define properties to the chip
    // such as color, shape, width
    Surface(
        color = when {
            selected -> MaterialTheme.colorScheme.primary
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colorScheme.onPrimary
            else -> Color.LightGray
        },
        shape = RoundedCornerShape(48.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colorScheme.primary
                else -> Color.LightGray
            },
        ),
        modifier = modifier,
    ) {
        // Inside a Row pack the Image and text together to
        // show inside the chip
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = null,
                modifier = Modifier
                    .padding(2.dp)
                    .size(28.dp)
                    .clip(CircleShape),
            )
            Text(
                text = text,
                style = typography.bodySmall,
                modifier = Modifier.padding(end = 12.dp, start = 4.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleResumeCardPreview() {
    ArticleResumeCard(Article.mockArticle)
}
