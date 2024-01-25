package com.pbi.newsapp.domain.repository

import arrow.core.Either
import com.pbi.newsapp.domain.model.NetworkError
import com.pbi.newsapp.domain.model.News

interface NewsRepository {

    suspend fun getNews(query: HashMap<String, Any>) : Either<NetworkError, News>

    suspend fun getHeadlines(query: HashMap<String, Any>) : Either<NetworkError, News>
}