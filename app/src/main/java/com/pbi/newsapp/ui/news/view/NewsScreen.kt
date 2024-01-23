package com.pbi.newsapp.ui.news.view

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pbi.newsapp.ui.news.viewmodel.NewsViewModel
import com.pbi.newsapp.ui.util.component.LoadingDialog

@Composable
internal fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    NewsContent(state = state)
}

@Composable
fun NewsContent(
    state: NewsViewState
) {
    LoadingDialog(isLoading = state.isLoading)

    // TODO: Log (news / articles) or iter articles
    state.news?.articles?.forEach {
        Log.i("api-success-articles-data", "NewsContent: ${it.title}")
    }

}