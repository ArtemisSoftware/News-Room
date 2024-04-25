package com.core.presentation.composables.pagination

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.artemissoftware.newsroom.core.common.exceptions.NewsRoomException
import com.core.domain.PaginationException
import com.core.presentation.util.toUiText
import com.core.ui.composables.UiText

@Composable
fun <T : Any>PaginationContent(
    items: LazyPagingItems<T>,
    loadingContent: @Composable () -> Unit,
    errorContent: @Composable (UiText) -> Unit,
    content: @Composable (LazyPagingItems<T>) -> Unit,
) {
    items.apply {
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
                errorContent(parseErrorMessage(error))
            }

            else -> {
                content(items)
            }
        }
    }
}

private fun parseErrorMessage(pagingError: LoadState.Error?): UiText {
    pagingError?.let {
        return when (val error = it.error) {
            is NewsRoomException -> {
                UiText.DynamicString(error.description)
            }
            is PaginationException -> {
                error.error.toUiText()
            }
            else -> {
                UiText.DynamicString(error.message ?: "Unknown Error.")
            }
        }
    }

    return UiText.DynamicString("Unknown Error.")
}
