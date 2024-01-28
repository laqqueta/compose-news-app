package com.pbi.newsapp.ui.news.view.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.ui.util.component.LoadingDialog

@Composable
fun ArticleLazyColumn(
    paddingValues: PaddingValues,
    articles: LazyPagingItems<Article>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .background(Color.Black)
    ) {
        items(articles.itemCount) {
            NewsDetails(article = articles[it]!!)

            Spacer(
                modifier = Modifier.padding(5.dp)
            )

            articles.apply {
                when {
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                        //You can add modifier to manage load state when first time response page is loading
                        LoadingDialog()
                        Log.i("loading-dialog", "showed")

                    }
                    loadState.append is LoadState.Error -> {
                        //You can use modifier to show error message
                    }
                }
            }
        }
    }
}