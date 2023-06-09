package com.news.domain.usecases

import com.news.domain.repositories.NewsRepository

class GetFeaturedArticlesUseCase /*@Inject*/ constructor(
    private val newsRepository: NewsRepository,
) {

    operator fun invoke() = newsRepository.getFeaturedArticles()
}
