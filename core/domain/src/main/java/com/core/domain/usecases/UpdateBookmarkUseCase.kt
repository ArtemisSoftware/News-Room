package com.core.domain.usecases

import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class UpdateBookmarkUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(article: Article) = with(newsRepository) {
        article.id?.let { id ->
            val result = getArticle(id = id)
            if (result == null) {
                save(article = article)
            } else {
                deleteArticle(article = result)
            }
        }
    }
}
