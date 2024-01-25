package com.pbi.newsapp.ui.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pbi.newsapp.data.paging.ArticlePagingSource
import com.pbi.newsapp.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val pagingSource: ArticlePagingSource,
) : ViewModel() {
    private val _articlePaging: MutableStateFlow<PagingData<Article>> =
        MutableStateFlow(PagingData.empty())
    var articlePaging = _articlePaging.asStateFlow()
        private set

    init {
        pagingSource()
    }

    private fun pagingSource() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                pagingSource
            }.flow.cachedIn(viewModelScope).collect {
                _articlePaging.value = it
            }
        }
    }

}