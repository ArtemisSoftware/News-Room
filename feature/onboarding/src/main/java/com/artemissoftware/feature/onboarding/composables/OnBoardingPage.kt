package com.artemissoftware.feature.onboarding.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.artemissoftware.feature.onboarding.PreviewData
import com.artemissoftware.newsroom.core.designsystem.ThemePreviews
import com.artemissoftware.newsroom.core.designsystem.theme.NewsRoomTheme
import com.artemissoftware.newsroom.core.designsystem.theme.palette
import com.artemissoftware.newsroom.core.designsystem.theme.spacing
import com.artemissoftware.newsroom.core.model.Page
import com.core.ui.R

@Composable
internal fun OnBoardingPage(
    page: Page,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f),
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing3))
        Text(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing4),
            text = page.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.palette.extraColors.displaySmall,
        )
        Text(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing4),
            text = page.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.palette.extraColors.textMedium,
        )
    }
}

@ThemePreviews
@Composable
private fun OnBoardingPagePreview() {
    NewsRoomTheme {
        OnBoardingPage(
            modifier = Modifier.fillMaxWidth(),
            page = PreviewData.pages[0],
            image = R.drawable.news_2,
        )
    }
}
