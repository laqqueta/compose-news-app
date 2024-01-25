package com.pbi.newsapp.utils

import androidx.paging.PagingSource
import com.pbi.newsapp.domain.model.Article

suspend fun PagingSource<Int, Article>.sendEvent(event: Any) {
    EventBus.sendEvent(event)
}