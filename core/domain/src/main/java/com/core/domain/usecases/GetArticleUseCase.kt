package com.core.domain.usecases

import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(id: Int) = newsRepository.getArticle(id)
}
