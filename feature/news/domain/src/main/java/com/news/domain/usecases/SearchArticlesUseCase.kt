package com.news.domain.usecases

import com.news.domain.Resource
import com.news.domain.models.Article
import com.news.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchArticlesUseCase /*@Inject*/ constructor(
    private val newsRepository: NewsRepository,
) {

    suspend operator fun invoke(map: MutableMap<String, String>): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(newsRepository.searchArticles(map)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}
