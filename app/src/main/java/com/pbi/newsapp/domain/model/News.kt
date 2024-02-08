package com.pbi.newsapp.domain.model

data class News(
    val status: String,
    val totalResult: Int,
    val articles: List<Article>
)
