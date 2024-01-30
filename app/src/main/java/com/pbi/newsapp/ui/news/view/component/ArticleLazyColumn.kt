package com.pbi.newsapp.ui.news.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import com.pbi.newsapp.R
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.ui.theme.lazyColumnBackground

@Composable
fun ArticleLazyColumn(
    paddingValues: PaddingValues,
    articles: LazyPagingItems<Article>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .background(lazyColumnBackground)
    ) {
        items(articles.itemCount) {
            NewsDetails(article = articles[it]!!)

            Spacer(
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.all_padding)
                )
            )
        }
    }
}