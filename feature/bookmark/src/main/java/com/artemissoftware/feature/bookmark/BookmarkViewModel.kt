package com.artemissoftware.feature.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.core.domain.usecases.GetPagedSavedArticlesUseCase
import com.core.domain.usecases.GetSavedArticlesUseCase
import com.core.presentation.util.constants.PresentationConstants.PAGINATION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase,
    private val getPagedSavedArticlesUseCase: GetPagedSavedArticlesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarkState())
    val state: StateFlow<BookmarkState> = _state.asStateFlow()

    init {
        getBookmarks()
    }

    private fun getBookmarks() {
        if (PAGINATION) {
            getPagedArticles()
        } else {
            getArticles()
        }
    }

    private fun getPagedArticles() = with(_state) {
        val articles = getPagedSavedArticlesUseCase().cachedIn(viewModelScope)

        update {
            it.copy(articlesPaged = articles)
        }
    }

    private fun getArticles() = with(_state) {
        viewModelScope.launch {
            getSavedArticlesUseCase().collect { articles ->
                update {
                    it.copy(articles = articles)
                }
            }
        }
    }
}
