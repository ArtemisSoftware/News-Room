package com.core.domain.usecases

import com.core.domain.constants.NewsSources
import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class SearchPagedArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(searchQuery: String, sources: List<String> = NewsSources.DEFAULT_SOURCES) = newsRepository.searchPagedArticles(searchQuery = searchQuery, sources = sources)
}
