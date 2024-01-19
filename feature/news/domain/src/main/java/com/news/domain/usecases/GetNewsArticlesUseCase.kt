package com.news.domain.usecases

import com.news.domain.repositories.NewsRepository
import javax.inject.Inject

class GetNewsArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(sources: List<String>) = newsRepository.getNews(sources = sources)
}
