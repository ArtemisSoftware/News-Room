package com.core.domain.usecases

import com.artemissoftware.newsroom.core.common.DataResponse
import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.constants.NewsSources
import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(sources: List<String> = NewsSources.DEFAULT_SOURCES): DataResponse<List<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}
