package com.pbi.newsapp.ui.news.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
    val everythingPagingData = viewModel.everythingPaging.collectAsLazyPagingItems()
    val headlinesPagingData = viewModel.headlinesPaging.collectAsLazyPagingItems()

    NewsContent(everythingPagingData, headlinesPagingData)
}

@Composable
fun NewsContent(
    pagerEverything: LazyPagingItems<Article>,
    pagerHeadlines: LazyPagingItems<Article>
) {
    Column {
//        LazyRow {
//            items(pagerHeadlines.itemCount) {
//                Text(text = pagerHeadlines[it]?.title ?: "empty", modifier = Modifier.padding(end = 20.dp))
//            }
//        }

//        Spacer(modifier = Modifier.padding(bottom = 50.dp))
        LazyColumn {
            items(pagerEverything.itemCount) {
                Text(text = pagerEverything[it]?.content ?: "empty", modifier = Modifier.padding(bottom = 20.dp))
            }

            pagerEverything.apply {
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
}
