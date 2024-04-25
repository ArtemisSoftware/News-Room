package com.core.domain.usecases

import com.core.domain.constants.NewsSources
import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class GetPagedNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(sources: List<String> = NewsSources.DEFAULT_SOURCES) = newsRepository.getPagedNews(sources)
}
