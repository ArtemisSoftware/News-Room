package com.news.domain.usecases

import com.news.domain.repositories.NewsRepository

class SearchArticlesUseCase /*@Inject*/ constructor(
    private val newsRepository: NewsRepository,
) {

    suspend operator fun invoke(
        map: MutableMap<String, String>,
    ) = newsRepository.searchArticles(map)
}
