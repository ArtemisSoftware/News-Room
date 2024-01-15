package com.news.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.domain.Resource
import com.news.domain.usecases.GetFeaturedArticlesUseCase
import com.news.domain.usecases.GetNewsArticlesUseCase
import com.news.domain.usecases.SearchArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
//    private val getNewsArticlesUseCase: GetNewsArticlesUseCase,
    private val searchArticlesUseCase: SearchArticlesUseCase,
    private val getFeaturedArticlesUseCase: GetFeaturedArticlesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state.asStateFlow()

    init {
        getFeaturedArticles()
    }

    fun onTriggerEvent(event: NewsEvents) {
        when (event) {
            NewsEvents.openArticle -> TODO()
            is NewsEvents.searchArticles -> {searchArticles(event.query)}
        }
    }

    private fun searchArticles(query: String) = with(_state) {
        viewModelScope.launch {
            searchArticlesUseCase(query = query).collectLatest { result ->
                when(result){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        update {
                            it.copy(articles = result.data)
                        }
                    }
                }
            }
        }
    }

    private fun getFeaturedArticles() = with(_state) {
        viewModelScope.launch {
            getFeaturedArticlesUseCase().collectLatest { result ->
                update {
                    it.copy(articles = result)
                }
            }
        }
    }
}
