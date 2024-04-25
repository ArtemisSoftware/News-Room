package com.core.domain.usecases

import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class GetPagedSavedArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke() = newsRepository.getArticlesPaged()
}
