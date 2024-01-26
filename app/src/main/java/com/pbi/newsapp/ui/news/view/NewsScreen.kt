package com.pbi.newsapp.ui.news.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.ui.news.viewmodel.NewsViewModel
import com.pbi.newsapp.ui.util.component.LoadingDialog
import com.pbi.newsapp.utils.Endpoint
import dagger.Lazy

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
        { viewModel.toHeadlines() }
    )
}


@Composable
fun NewsContent(
    newsPagingData: LazyPagingItems<Article>,
    headlinesPagingData: LazyPagingItems<Article>,
    state: NewsViewState,
    newsOnClick: () -> Unit,
    headlinesOnClick: () -> Unit
) {
    Column {

        Button(onClick = newsOnClick) {
            Text(text = "News Page")
        }

        Button(onClick = headlinesOnClick) {
            Text(text = "Headlines Page")
        }

        when(state.endpoint) {
            Endpoint.EVERYTHING -> ArticleView(pagingData = newsPagingData)
            else -> ArticleView(pagingData = headlinesPagingData)
        }

    }
}

@Composable
fun ArticleView(pagingData: LazyPagingItems<Article>) {
    LazyColumn {
        items(pagingData.itemCount) {
            Text(text = pagingData[it]?.title ?: "empty", modifier = Modifier.padding(bottom = 20.dp))
        }

        pagingData.apply {
            when {
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when first time response page is loading
                    item {
                        LoadingDialog()
                        Log.i("loading-dialog", "showed")
                    }
                }
                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
        }

    }
}
