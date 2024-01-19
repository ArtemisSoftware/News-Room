package com.core.data

import com.core.data.database.entities.ArticleEntity
import com.core.data.remote.dto.ArticleDto
import com.core.data.remote.dto.SourceDto
import com.core.domain.models.Article
import com.core.domain.models.Source

internal object TestData {

    val sourceDto = SourceDto(
        id = "marca",
        name = "Marca",
    )

    val articleDto = ArticleDto(
        author = "apnews.com",
        content = "Asian shares traded mixed Thursday as pessimism spread among investors about any imminent interest rate cut in the United States.Japan's benchmark Nikkei was little changed, inching down less than 0.… [+302 chars]",
        description = "Asian shares traded mixed Thursday as pessimism spread among investors about any imminent interest rate cut in the United States. Japan's benchmark Nikkei was little changed, inching down less than 0.1% to finish at 35,466.17. Australia's S&P/ASX 200 slipped …",
        publishedAt = "2024-01-18T06:44:08Z",
        source = sourceDto,
        title = "Stephen A. Smith rejects Fox News host's attempt to use him as proof against DEI",
        url = "https://www.marca.com/en/lifestyle/celebrities/2024/01/18/65a8b4a022601da5028b45f7.html",
        urlToImage = "https://phantom-marca.unidadeditorial.es/45aa69fd401bc12d140708f464ec77d5/resize/1200/f/webp/assets/multimedia/imagenes/2024/01/18/17055537636828.jpg",
    )

    val source = Source(
        name = "Marca",
    )

    val article = Article(
        author = "apnews.com",
        content = "Asian shares traded mixed Thursday as pessimism spread among investors about any imminent interest rate cut in the United States.Japan's benchmark Nikkei was little changed, inching down less than 0.… [+302 chars]",
        description = "Asian shares traded mixed Thursday as pessimism spread among investors about any imminent interest rate cut in the United States. Japan's benchmark Nikkei was little changed, inching down less than 0.1% to finish at 35,466.17. Australia's S&P/ASX 200 slipped …",
        publishedAt = "2024-01-18T06:44:08Z",
        title = "Stephen A. Smith rejects Fox News host's attempt to use him as proof against DEI",
        url = "https://www.marca.com/en/lifestyle/celebrities/2024/01/18/65a8b4a022601da5028b45f7.html",
        urlToImage = "https://phantom-marca.unidadeditorial.es/45aa69fd401bc12d140708f464ec77d5/resize/1200/f/webp/assets/multimedia/imagenes/2024/01/18/17055537636828.jpg",
        source = source,
    )

    val articleEntity = ArticleEntity(
        author = "apnews.com",
        content = "Asian shares traded mixed Thursday as pessimism spread among investors about any imminent interest rate cut in the United States.Japan's benchmark Nikkei was little changed, inching down less than 0.… [+302 chars]",
        description = "Asian shares traded mixed Thursday as pessimism spread among investors about any imminent interest rate cut in the United States. Japan's benchmark Nikkei was little changed, inching down less than 0.1% to finish at 35,466.17. Australia's S&P/ASX 200 slipped …",
        publishedAt = "2024-01-18T06:44:08Z",
        title = "Stephen A. Smith rejects Fox News host's attempt to use him as proof against DEI",
        url = "https://www.marca.com/en/lifestyle/celebrities/2024/01/18/65a8b4a022601da5028b45f7.html",
        urlToImage = "https://phantom-marca.unidadeditorial.es/45aa69fd401bc12d140708f464ec77d5/resize/1200/f/webp/assets/multimedia/imagenes/2024/01/18/17055537636828.jpg",
        source = source,
    )
}
