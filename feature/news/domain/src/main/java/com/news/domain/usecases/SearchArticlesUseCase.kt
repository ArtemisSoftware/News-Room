package com.news.domain.usecases

import com.news.domain.Resource
import com.news.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchArticlesUseCase /*@Inject*/ constructor(
    private val newsRepository: NewsRepository,
) {

//    suspend operator fun invoke(query: String): Flow<Resource<List<Article>>> = flow {
////        emit(Resource.Loading())
////        try {
////            val result = newsRepository.searchArticles(query = query)
////            emit(Resource.Success(result))
////        } catch (e: Exception) {
////            emit(Resource.Error(e.message.toString()))
////        }
//    }
}
