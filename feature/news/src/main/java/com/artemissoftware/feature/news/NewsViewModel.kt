package com.artemissoftware.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
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
        }
    }

    private fun getNews() = with(_state) {
        viewModelScope.launch {
            val result = getNewsUseCase(sources = listOf("bbc-news", "abc-news", "al-jazeera-english"))
            update {
                it.copy(articles = result)
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
}
