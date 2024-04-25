package com.core.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemissoftware.newsroom.core.network.dto.ArticleDto
import com.artemissoftware.newsroom.core.network.source.NewsApiSource
import com.core.data.HandleNetwork
import com.core.domain.PaginationException
import com.core.domain.DataResponse
import com.core.domain.error.DataError

class SearchArticlesPagingSource(
    private val newsApiSource: NewsApiSource,
    private val searchQuery: String,
    private val sources: String,
) : PagingSource<Int, ArticleDto>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        val currentPage = params.key ?: 1

        val result = HandleNetwork.safeNetworkCall {
            newsApiSource.search(searchQuery = searchQuery, sources = sources, page = currentPage)
        }

        return when (result) {
            is DataResponse.Failure -> {
                LoadResult.Error(throwable = PaginationException(result.error as DataError.NetworkError))
            }
            is DataResponse.Success -> {
                val news = result.data
                totalNewsCount += news.articles.size
                val articles = news.articles.distinctBy { it.title } // Remove duplicates

                var data = emptyList<ArticleDto>()
                var prevKey: Int? = null
                var nextKey: Int? = null

                if (totalNewsCount <= news.totalResults) {
                    val endOfPaginationReached = news.articles.isEmpty()
                    data = articles
                    prevKey = if (currentPage == 1) null else currentPage - 1
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                }

                LoadResult.Page(
                    data = data,
                    prevKey = prevKey,
                    nextKey = nextKey,
                )
            }
        }


//        return try {
//            val news = newsApiSource.search(searchQuery = searchQuery, sources = sources, page = currentPage)
//
//            totalNewsCount += news.articles.size
//            val articles = news.articles.distinctBy { it.title } // Remove duplicates
//
//            val endOfPaginationReached = news.articles.isEmpty()
//
//            if (totalNewsCount <= news.totalResults) {
//                LoadResult.Page(
//                    data = articles,
//                    prevKey = if (currentPage == 1) null else currentPage - 1,
//                    nextKey = if (endOfPaginationReached) null else currentPage + 1,
//                )
//            } else {
//                LoadResult.Page(
//                    data = emptyList(),
//                    prevKey = null,
//                    nextKey = null,
//                )
//            }
//        } catch (e: Exception) {
//            LoadResult.Error(throwable = e)
//        }
    }
}
