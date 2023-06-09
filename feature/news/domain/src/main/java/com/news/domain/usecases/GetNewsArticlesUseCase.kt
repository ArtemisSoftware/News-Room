package com.news.domain.usecases

import com.news.domain.repositories.NewsRepository

class GetNewsArticlesUseCase /*@Inject*/ constructor(
    private val newsRepository: NewsRepository,
) {

    suspend operator fun invoke(
        country: String,
        category: String,
    ) = newsRepository.getNewsArticle(country = country, category = category)
}
