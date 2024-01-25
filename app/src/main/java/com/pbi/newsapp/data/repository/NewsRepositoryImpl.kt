package com.pbi.newsapp.data.repository

import arrow.core.Either
import com.pbi.newsapp.data.mapper.toNetworkError
import com.pbi.newsapp.data.remote.NewsApi
import com.pbi.newsapp.domain.model.NetworkError
import com.pbi.newsapp.domain.model.News
import com.pbi.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getNews(query: HashMap<String, Any>): Either<NetworkError, News> {
        return Either.catch {
            newsApi.getNews(query = query)
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getHeadlines(query: HashMap<String, Any>): Either<NetworkError, News> {
        return Either.catch {
            newsApi.getHeadlines(query = query)
        }.mapLeft { it.toNetworkError() }
    }



}