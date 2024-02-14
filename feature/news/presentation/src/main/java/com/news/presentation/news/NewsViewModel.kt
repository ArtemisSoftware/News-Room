package com.news.presentation.news
/*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.models.Article
import com.news.domain.usecases.GetNewsArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state.asStateFlow()

    init {
        getArticles()
    }

    fun onTriggerEvent(event: NewsEvents) {
        when (event) {
            is NewsEvents.UpdateMaxScrollingValue -> updateMaxScrollingValue(event.value)
            is NewsEvents.UpdateScrollValue -> updateScrollValue(event.value)
            NewsEvents.openArticle -> TODO()
        }
    }

    private fun updateArticles(articles: List<Article>) = with(_state) {
        update {
            it.copy(articles = articles)
        }
    }

    private fun updateMaxScrollingValue(value: Int) = with(_state) {
        update {
            it.copy(maxScrollingValue = value)
        }
    }

    private fun updateScrollValue(value: Int) = with(_state) {
        update {
            it.copy(scrollValue = value)
        }
    }

    private fun getArticles() {
        viewModelScope.launch {
            val articles = getNewsArticlesUseCase(listOf("bbc-news", "abc-news", "al-jazeera-english"))

            updateArticles(articles = articles)
        }
    }
}
*/