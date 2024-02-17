package com.core.domain.usecases

import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(id: Int): Article? {
        return newsRepository.getArticle(id)
    }
}
