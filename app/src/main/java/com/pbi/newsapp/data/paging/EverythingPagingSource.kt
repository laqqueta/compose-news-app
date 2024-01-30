package com.pbi.newsapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import arrow.core.Either
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.domain.model.request.EverythingEndpointParam
import com.pbi.newsapp.domain.repository.NewsRepository
import com.pbi.newsapp.utils.Event
import com.pbi.newsapp.utils.sendEvent
import kotlinx.coroutines.delay
import javax.inject.Inject

class EverythingPagingSource @Inject constructor(
    private val repository: NewsRepository,
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        delay(3000) // emit load state
        val page = params.key ?: 1
        val param = EverythingEndpointParam(
            q = "pemilu",
            source = "id",
            pageSize = params.loadSize,
            page = page
        )

        Log.i("api-paging-news-source-page", "page: $page")

        return when(val response = repository.getNews(param.toHashMap())) {
            is Either.Left -> {
                response.onLeft {
                    sendEvent(Event.ToastMessage(it.error.message))
                }
                // pass Throwable to play safe
                LoadResult.Error(response.leftOrNull()?.t ?: Throwable(message = "Unknown Error"))
            }
            is Either.Right -> {
                var data: List<Article> = emptyList()

                response.onRight {
                    data = it.articles
                    Log.i("api-paging-news-source-status", "status: ${it.status}")
                }

                LoadResult.Page(
                    data = data,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (data.isEmpty()) null else page.plus(1),
                )
            }
        }
    }
}

