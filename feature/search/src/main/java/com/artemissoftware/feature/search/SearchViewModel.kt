package com.artemissoftware.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.core.domain.usecases.SearchArticlesUseCase
import com.core.domain.usecases.SearchPagedArticlesUseCase
import com.core.presentation.util.constants.PresentationConstants.PAGINATION
import com.core.presentation.util.toUiText
import com.core.ui.composables.DialogData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchArticlesUseCase: SearchArticlesUseCase,
    private val searchPagedArticlesUseCase: SearchPagedArticlesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    fun onTriggerEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                updateSearchQuery(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchNews -> {
                search()
            }

            is SearchEvent.ActivateSearch -> activateSearch(event.isActive)
            SearchEvent.CloseDialog -> updateDialog()
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

    private fun activateSearch(isActive: Boolean) = with(_state) {
        update {
            it.copy(isSearching = isActive)
        }
    }

    private fun search() {
        updateHistory()
        if (PAGINATION) {
            searchPagedArticles()
        } else {
            searchArticles()
        }
    }

    private fun searchArticles() = with(_state) {
        viewModelScope.launch {
            searchArticlesUseCase(searchQuery = value.searchQuery)
                .onSuccess { articles ->
                    update {
                        it.copy(articles = articles)
                    }
                }
                .onFailure {
                    updateDialog(dialogData = DialogData(it.toUiText()))
                }
        }
    }

    private fun searchPagedArticles() = with(_state) {
        val articles = searchPagedArticlesUseCase(searchQuery = value.searchQuery)
            .cachedIn(viewModelScope)

        update {
            it.copy(articlesPaged = articles)
        }
    }

    private fun updateDialog(dialogData: DialogData? = null) = with(_state) {
        update {
            it.copy(dialogData = dialogData)
        }
    }
}
