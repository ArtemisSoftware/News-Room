package com.news.domain.usecases

import com.news.domain.repositories.NewsRepository
import javax.inject.Inject

class GetFeaturedArticlesUseCase /*@Inject*/ constructor(
    private val newsRepository: NewsRepository,
) {

    operator fun invoke() = null//newsRepository.getFeaturedArticles()
}
