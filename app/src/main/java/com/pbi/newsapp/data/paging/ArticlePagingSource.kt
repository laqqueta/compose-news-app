package com.pbi.newsapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import arrow.core.left
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.domain.model.request.EverythingEndpointParam
import com.pbi.newsapp.domain.repository.NewsRepository
import com.pbi.newsapp.utils.Endpoint
import com.pbi.newsapp.utils.Event
import com.pbi.newsapp.utils.EventBus
import javax.inject.Inject

class ArticlePagingSource @Inject constructor(
    private val repository: NewsRepository,
) : PagingSource<Int, Article>() {

    // TODO: move endpoint variable to param
    private var endpoint: Endpoint = Endpoint.SOURCE

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        return try {
            val page = params.key ?: 1

            val requestParam = when(endpoint) {
                Endpoint.EVERYTHING -> EverythingEndpointParam(
                    q = "pemilu",
                    source = "id",
                    pageSize = params.loadSize,
                    page = page
                )
                else -> throw UnsupportedOperationException()
            }

            Log.i("api-paging-source-page", "page: $page")

            val response = repository.getNews(requestParam.toHashMap()).getOrNull()!!

            Log.i("api-paging-source-article", "page: ${response.articles}")

            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            Log.e("api-paging-source-err", e.stackTraceToString() )
            // TODO: make event message more convenient
            event(Event.ToastMessage(e.javaClass.simpleName))
            LoadResult.Error(e)
        }

    }

    private suspend fun event(event: Any) {
        EventBus.sendEvent(event)
    }
}

