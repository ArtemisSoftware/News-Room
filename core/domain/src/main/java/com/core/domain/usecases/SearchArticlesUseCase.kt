package com.core.domain.usecases

import com.core.domain.constants.NewsSources.DEFAULT_SOURCES
import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class SearchArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(searchQuery: String, sources: List<String> = DEFAULT_SOURCES) = newsRepository.searchArticles(searchQuery = searchQuery, sources = sources)
}
