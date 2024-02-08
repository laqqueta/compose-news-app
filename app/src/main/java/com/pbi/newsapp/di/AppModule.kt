package com.pbi.newsapp.di

import com.pbi.newsapp.data.remote.NewsApi
import com.pbi.newsapp.utils.Constant.API_KEY
import com.pbi.newsapp.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun setUpApiKey(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .url(chain.request().url
                .newBuilder()
                .addQueryParameter("apiKey", API_KEY)
                .build())
            .build()
    )

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { setUpApiKey(it) }
        .build()

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsApi::class.java)
    }



}