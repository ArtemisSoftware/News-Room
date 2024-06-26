package com.artemissoftware.feature

import androidx.paging.PagingData
import com.artemissoftware.feature.search.SearchState
import com.artemissoftware.newsroom.core.model.Article
import com.artemissoftware.newsroom.core.model.Source
import kotlinx.coroutines.flow.flowOf

internal object PreviewData {

    val article = Article(
        author = "",
        title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
        description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
        content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
        publishedAt = "2023-06-16T22:24:33Z",
        source = Source(
            name = "bbc",
        ),
        url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1",
        urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg",
    )

    val mock = flowOf(PagingData.from(listOf(article)))

    val searchStateEmpty = SearchState(
        searchQuery = "",
        isSearching = false,
        historyItems = emptyList(),
        articles = listOf(article),
    )

    val searchState = SearchState(
        searchQuery = "Debate",
        isSearching = true,
        historyItems = listOf("War", "Fashion"),
        articles = emptyList(),
    )
}
