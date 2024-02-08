package com.pbi.newsapp.ui.news.view.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.ui.util.component.LoadingDialog

@Composable
fun LoadingAnimation(pagingData: LazyPagingItems<Article>) {
    pagingData.apply {
        when {
            loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                LoadingDialog()
                Log.i("loading-dialog", "showed")

            }
            loadState.append is LoadState.Error -> {

            }
        }
    }
}