package com.pbi.newsapp.ui.news.view

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.ui.news.view.component.ArticleLazyColumn
import com.pbi.newsapp.ui.news.view.component.BottomBarButton
import com.pbi.newsapp.ui.news.view.component.LoadingAnimation
import com.pbi.newsapp.utils.Endpoint

@Composable
fun NewsContent(
    newsPagingData: LazyPagingItems<Article>,
    headlinesPagingData: LazyPagingItems<Article>,
    state: NewsViewState,
    newsOnClick: () -> Unit,
    headlinesOnClick: () -> Unit,
) {
    Scaffold(bottomBar = {
        BottomAppBar(
            containerColor = Color(0xFF1b1b1c),
            modifier = Modifier
                .height(50.dp)
                .padding(0.dp),
            actions = {
                BottomBarButton(
                    focusRequester = state.newsFocus,
                    icon = state.newsIcon,
                    modifier = Modifier.weight(1f),
                    onClick = newsOnClick
                )
                BottomBarButton(
                    focusRequester = state.headlinesFocus,
                    icon = state.headlinesIcon,
                    modifier = Modifier.weight(1f),
                    onClick = headlinesOnClick
                )
            }
        )
    }) {
        when(state.endpoint) {
            Endpoint.EVERYTHING -> {
                LoadingAnimation(pagingData = newsPagingData)
                ArticleLazyColumn(paddingValues = it, articles = newsPagingData)
            }
            else -> {
                LoadingAnimation(pagingData = headlinesPagingData)
                ArticleLazyColumn(paddingValues = it, articles = headlinesPagingData)
            }
        }
    }
}
