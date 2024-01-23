package com.pbi.newsapp.data.remote

import com.pbi.newsapp.domain.model.News
import retrofit2.http.GET

interface NewsApi {
    @GET("everything?pageSize=5&q=pemilu")
    suspend fun getNews() : News
}