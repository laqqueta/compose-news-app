package com.pbi.newsapp.ui.news.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbi.newsapp.domain.repository.NewsRepository
import com.pbi.newsapp.ui.news.view.NewsViewState
import com.pbi.newsapp.ui.util.sendEvent
import com.pbi.newsapp.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NewsViewState())
    val state = _state.asStateFlow()

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            // Emit Loading
            _state.update {
                newsState -> newsState.copy(isLoading = true)
            }

            newsRepository.getNews()
                .onRight { news ->
                    _state.update {
                        newsViewState -> newsViewState.copy(news = news)
                    }
                }
                .onLeft {newsError ->
                    _state.update {
                        newsViewState -> newsViewState.copy(error = newsError.error.message)
                    }

                    sendEvent(Event.ToastMessage(newsError.error.message))

                    // Log Error
                    Log.e("api-error", newsError.t!!.stackTraceToString())
                }

            _state.update {
                    newsState -> newsState.copy(isLoading = false)
            }
        } // End viewModelScope
    }

}