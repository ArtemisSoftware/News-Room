package com.core.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.newsroom.core.model.Article

@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    val message by remember {
        mutableStateOf(parseErrorMessage(pagingError = error))
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            // ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(message = message)
            false
        }

        else -> {
            true
        }
    }
}

private fun parseErrorMessage(pagingError: LoadState.Error?): String {

    pagingError?.let {
        //val error = it.error as News
    }


//    if (error == null){
//        message = "You have not saved news so far !"
//        icon = R.drawable.ic_search_document
//    }

    return "Unknown Error."
//    when (error?.error) {
//        is SocketTimeoutException -> {
//            "Server Unavailable."
//        }
//
//        is ConnectException -> {
//            "Internet Unavailable."
//        }
//
//        else -> {*/
//            "Unknown Error."
//        }
//    }
}