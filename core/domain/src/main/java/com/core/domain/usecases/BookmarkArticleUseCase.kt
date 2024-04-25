package com.core.domain.usecases

import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.repository.NewsRepository
import javax.inject.Inject

class BookmarkArticleUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(article: Article) = newsRepository.updateBookmark(article)
}
