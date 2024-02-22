package com.core.domain.usecases

import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(sources: List<String>): List<Article> {
        return newsRepository.getNews(sources = sources)
    }
}