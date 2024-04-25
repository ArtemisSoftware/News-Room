package com.core.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemissoftware.newsroom.core.database.dao.NewsDao
import com.artemissoftware.newsroom.core.model.Article

class BookmarkPagingSource (
    private val dao: NewsDao
) /*: PagingSource<Int, Article>() */{

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
//        val page = params.key ?: 0
//
//        return try {
//            val entities = dao.getPagedList(params.loadSize, page * params.loadSize)
//
//            // simulate page loading
//            if (page != 0) delay(1000)
//
//            LoadResult.Page(
//                data = entities,
//                prevKey = if (page == 0) null else page - 1,
//                nextKey = if (entities.isEmpty()) null else page + 1
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//        TODO("Not yet implemented")
//    }



    //    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
//        val page = params.key ?: 0
//
//        return try {
//            val entities = dao.getPagedList(params.loadSize, page * params.loadSize)
//
//            // simulate page loading
//            if (page != 0) delay(1000)
//
//            LoadResult.Page(
//                data = entities,
//                prevKey = if (page == 0) null else page - 1,
//                nextKey = if (entities.isEmpty()) null else page + 1
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }

}