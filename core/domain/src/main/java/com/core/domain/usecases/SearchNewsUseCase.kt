package com.core.domain.usecases

import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(searchQuery: String, sources: List<String>) = newsRepository.searchArticles(searchQuery = searchQuery, sources = sources)

    // TODO: fazer quando houver paginaçao
//    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
//        return newsRepository.searchNews(
//            searchQuery = searchQuery,
//            sources = sources
//        )
//    }
}
