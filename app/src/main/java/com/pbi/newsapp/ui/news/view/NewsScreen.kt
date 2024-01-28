package com.pbi.newsapp.ui.news.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.pbi.newsapp.ui.news.view.component.NewsContent
import com.pbi.newsapp.ui.news.viewmodel.NewsViewModel

@Composable
internal fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val everythingPagingData = viewModel.everythingPaging.collectAsLazyPagingItems()
    val headlinesPagingData = viewModel.headlinesPaging.collectAsLazyPagingItems()
    val state by viewModel.state.collectAsStateWithLifecycle()

    NewsContent(
        everythingPagingData,
        headlinesPagingData,
        state,
        { viewModel.toNews() },
        { viewModel.toHeadlines() },
    )
}
