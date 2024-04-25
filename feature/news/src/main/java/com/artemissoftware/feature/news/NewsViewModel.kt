package com.artemissoftware.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.repository.NewsRepository
import com.core.presentation.util.asUiText
import com.core.presentation.util.constants.PresentationConstants
import com.core.ui.composables.DialogData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state.asStateFlow()

//    val news = getNewsUseCase(
//        sources = listOf("bbc-news","abc-news","al-jazeera-english")
//    ).cachedIn(viewModelScope)

    init {
        getArticles()
    }

    fun onTriggerEvent(event: NewsEvent) {
        when (event) {
            is NewsEvent.UpdateScrollValue -> updateScrollValue(event.newValue)
            is NewsEvent.UpdateMaxScrollingValue -> updateMaxScrollingValue(event.newValue)
            NewsEvent.CloseDialog -> updateDialog()
        }
    }

    private fun getArticles() {
        if (PresentationConstants.PAGINATION) {
            getPagedNews()
        } else {
            getNews()
        }
    }

    private fun getNews() {
        viewModelScope.launch {
            newsRepository.getNews()
                .onSuccess {
                    updateArticles(it)
                }
                .onFailure {
                    updateDialog(dialogData = DialogData(it.asUiText()))
                }
        }
    }

    private fun getPagedNews() = with(_state) {
//        val articles = searchPagedArticledUseCase(searchQuery = value.searchQuery)
//            .cachedIn(viewModelScope)
//
//        update {
//            it.copy(articlesPaged = articles)
//        }
    }

    private fun updateScrollValue(newValue: Int) = with(_state) {
        update {
            it.copy(scrollValue = newValue)
        }
    }

    private fun updateMaxScrollingValue(newValue: Int) = with(_state) {
        update {
            it.copy(maxScrollingValue = newValue)
        }
    }

    private fun updateArticles(articles: List<Article>) = with(_state) {
        update {
            it.copy(articles = articles)
        }
    }

    private fun updateDialog(dialogData: DialogData? = null) = with(_state) {
        update {
            it.copy(dialogData = dialogData)
        }
    }
}
