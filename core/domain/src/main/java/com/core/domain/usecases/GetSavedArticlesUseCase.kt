package com.core.domain.usecases

import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke() = newsRepository.getArticles()
}
