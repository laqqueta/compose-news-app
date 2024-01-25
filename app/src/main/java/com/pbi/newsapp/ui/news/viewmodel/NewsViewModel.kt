package com.pbi.newsapp.ui.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pbi.newsapp.data.paging.EverythingPagingSource
import com.pbi.newsapp.data.paging.HeadlinesPagingSource
import com.pbi.newsapp.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val everythingSource: EverythingPagingSource,
    private val headlinesSource: HeadlinesPagingSource
) : ViewModel() {

    private val _everythingPaging: MutableStateFlow<PagingData<Article>> =
        MutableStateFlow(PagingData.empty())

    private val _headlinesPaging: MutableStateFlow<PagingData<Article>> =
        MutableStateFlow(PagingData.empty())

    var everythingPaging = _everythingPaging.asStateFlow()
        private set

    var headlinesPaging = _headlinesPaging.asStateFlow()
        private set

    init {
        everythingPagingSource()
        headlinesPagingSource()
    }

    private fun everythingPagingSource() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                everythingSource
            }.flow.cachedIn(viewModelScope).collect {
                _everythingPaging.value = it
            }

        }
    }

    private fun headlinesPagingSource() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                headlinesSource
            }.flow.cachedIn(viewModelScope).collect {
                _headlinesPaging.value = it
            }
        }
    }

}