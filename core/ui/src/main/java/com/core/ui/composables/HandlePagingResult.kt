package com.core.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.newsroom.core.common.exceptions.NewsRoomException
import com.artemissoftware.newsroom.core.model.Article
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun Paging(
    loadState: CombinedLoadStates,
    loadingContent: @Composable () -> Unit,
    errorContent: @Composable (LoadState.Error?) -> Unit,
    content: @Composable () -> Unit,
) {
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    when {
        loadState.refresh is LoadState.Loading -> {
            loadingContent()
        }

        error != null -> {
            errorContent(error)
        }

        else -> {
            content()
        }
    }
}

@Composable
fun handlePagingResult(loadState: CombinedLoadStates): PaginationResult {
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            PaginationResult.LOADING
        }

        error != null -> {
            PaginationResult.ERROR
        }

        else -> {
            PaginationResult.COMPLETE
        }
    }
}

@Composable
fun handlePagingResult__(articles: LazyPagingItems<Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    val message by remember(error) {
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

fun parseErrorMessage(pagingError: LoadState.Error?): String {
    pagingError?.let {
        return when (val error = it.error) {
            is NewsRoomException -> {
                error.description
            }
            is SocketTimeoutException -> {
                "Server Unavailable."
            }

            is ConnectException -> {
                "Internet Unavailable."
            }
            else -> {
                error.message ?: "Unknown Error."
            }
        }
    }

    return "Unknown Error."
}
