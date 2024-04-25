package com.core.domain.usecases

import com.core.domain.constants.NewsSources
import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class SearchArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(searchQuery: String, sources: List<String> = NewsSources.DEFAULT_SOURCES) = newsRepository.searchArticles(searchQuery = searchQuery, sources = sources)
}
