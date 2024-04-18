package com.artemissoftware.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.usecases.GetNewsUseCase
import com.core.ui.composables.DialogData
import com.core.ui.composables.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state.asStateFlow()

//    val news = getNewsUseCase(
//        sources = listOf("bbc-news","abc-news","al-jazeera-english")
//    ).cachedIn(viewModelScope)

    init {
        getNews()
    }

    fun onTriggerEvent(event: NewsEvent) {
        when (event) {
            is NewsEvent.UpdateScrollValue -> updateScrollValue(event.newValue)
            is NewsEvent.UpdateMaxScrollingValue -> updateMaxScrollingValue(event.newValue)
            NewsEvent.CloseDialog -> updateDialog(show = false)
        }
    }

    private fun getNews() = with(_state) {
        viewModelScope.launch {
            getNewsUseCase()
                .onSuccess {
                    updateArticles(it)
                }
                .onFailure {
                    updateDialog(show = true, message = it.asUiText())
                }
        }
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

    private fun updateDialog(show: Boolean, message: UiText? = null) = with(_state) {
        message?.let { text ->
            update {
                it.copy(showDialog = show, dialogData = DialogData(text))
            }
        } ?: run {
            update {
                it.copy(showDialog = show)
            }
        }
    }
}
