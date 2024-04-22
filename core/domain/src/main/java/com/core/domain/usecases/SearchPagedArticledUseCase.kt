package com.core.domain.usecases

import androidx.paging.PagingData
import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.constants.NewsSources
import com.core.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPagedArticledUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(searchQuery: String, sources: List<String> = NewsSources.DEFAULT_SOURCES): Flow<PagingData<Article>> {
        return newsRepository.searchPagedArticles(
            searchQuery = searchQuery,
            sources = sources,
        )
    }
}
