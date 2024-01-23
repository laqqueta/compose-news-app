package com.pbi.newsapp.data.remote

import com.pbi.newsapp.domain.model.News
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApi {
    @GET("everything")
    suspend fun getNews(@QueryMap queryMap: Map<String, String>) : News
}