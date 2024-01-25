package com.pbi.newsapp.ui.news.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.ui.news.viewmodel.NewsViewModel
import com.pbi.newsapp.ui.util.component.LoadingDialog

@Composable
internal fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val newsPaging = viewModel.articlePaging.collectAsLazyPagingItems()

    NewsContent(newsPaging)
}

@Composable
fun NewsContent(
    pager: LazyPagingItems<Article>
) {
    Column {
        Button(onClick = {}) {
            Text(text = "Update News to Next Page")
        }

        LazyColumn {
            items(pager.itemCount) {
                Text(text = pager[it]?.content ?: "empty", modifier = Modifier.padding(bottom = 20.dp))
            }

            pager.apply {
                when {
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading ->  {
                        item {
                            LoadingDialog()
                        }
                    }
                }
            }
        }
    }
}
