package com.artemissoftware.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.newsroom.core.model.Article
import com.core.domain.usecases.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    val sources = listOf("bbc-news", "abc-news", "al-jazeera-english")

    fun onTriggerEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                updateSearchQuery(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchNews -> {
                searchNews()
            }

            is SearchEvent.ActivateSearch -> activateSearch(event.isActive)
        }
    }

    private fun updateSearchQuery(searchQuery: String) = with(_state) {
        update {
            it.copy(searchQuery = searchQuery)
        }
    }

    private fun updateHistory() = with(_state) {
        val list = value.historyItems.toMutableList()
        list.add(value.searchQuery)
        update {
            it.copy(historyItems = list, isSearching = false)
        }
    }

    private fun updateArticles(articles: List<Article>) = with(_state) {
        update {
            it.copy(articles = articles)
        }
    }

    private fun activateSearch(isActive: Boolean) = with(_state) {
        update {
            it.copy(isSearching = isActive)
        }
    }

    private fun searchNews() = with(_state) {
        updateHistory()

        viewModelScope.launch {
            val result = searchNewsUseCase(searchQuery = value.searchQuery, sources = sources)
            updateArticles(result)
        }

        // TODO: fazer quando houver paginaçao
//        val articles = searchNewsUseCase(
//            searchQuery = _state.value.searchQuery,
//            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
//        ).cachedIn(viewModelScope)

        // --updateArticles(articles = articles)
    }
}
