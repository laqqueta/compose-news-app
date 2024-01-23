package com.pbi.newsapp.ui.news.view

import com.pbi.newsapp.domain.model.News

data class NewsViewState(
    val isLoading: Boolean = false,
    val news: News? = null,
    val error: String? = null
)