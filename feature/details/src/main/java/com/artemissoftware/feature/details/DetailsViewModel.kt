package com.artemissoftware.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.artemissoftware.feature.navigation.ArticleNavType
import com.artemissoftware.feature.navigation.NavArguments
import com.artemissoftware.feature.navigation.getValue
import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.repository.NewsRepository
import com.core.ui.uievents.UiEvent
import com.core.ui.uievents.UiEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    savedStateHandle: SavedStateHandle,
) : UiEventViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    init {
        ArticleNavType.getValue(savedStateHandle, NavArguments.ARTICLE)?.let {
            updateArticle(it)
        }
    }

    fun onTriggerEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.UpdateBookMark -> updateBookMark()
            DetailsEvent.OpenWebContent -> openWebContent()
            DetailsEvent.ShareArticle -> shareArticle()
        }
    }

    private fun getArticle(id: Int) {
        viewModelScope.launch {
            //val result = getArticleUseCase(id = id)
            //updateArticle(article = result)
        }
    }

    private fun updateArticle(article: Article? = null) = with(_state) {
        update {
            it.copy(article = article, isBookmarked = article?.id != null)
        }
    }

    private fun openWebContent() = with(_state.value) {
        article?.let {
            viewModelScope.launch {
                sendUiEvent(UiEvent.OpenWebBrowser(url = it.url))
            }
        }
    }

    private fun shareArticle() = with(_state.value) {
        article?.let {
            viewModelScope.launch {
                sendUiEvent(UiEvent.Share(url = it.url))
            }
        }
    }

    private fun updateBookMark() = with(_state) {
        viewModelScope.launch {
            value.article?.let { newsRepository.updateBookmark(it) }
            update {
                it.copy(isBookmarked = !it.isBookmarked)
            }
        }
    }
}
