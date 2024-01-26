package com.pbi.newsapp.ui.news.view

import com.pbi.newsapp.utils.Endpoint

data class NewsViewState(
    val endpoint: Endpoint = Endpoint.EVERYTHING
)
