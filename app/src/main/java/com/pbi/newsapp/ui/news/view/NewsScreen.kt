package com.pbi.newsapp.ui.news.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pbi.newsapp.ui.news.viewmodel.NewsViewModel
import com.pbi.newsapp.ui.util.component.LoadingDialog

@Composable
internal fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var i by remember {
        mutableIntStateOf(1)
    }

    NewsContent(state = state) { viewModel.getNews(++i) }
}

@Composable
fun NewsContent(
    state: NewsViewState,
    updateNews: () -> Unit
) {
    LoadingDialog(isLoading = state.isLoading)

    Button(onClick = updateNews) {
        Text(text = "Update News to Next Page")
    }

}