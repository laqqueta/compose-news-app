package com.pbi.newsapp.ui.news.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.pbi.newsapp.R
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.ui.theme.NewsAppTheme
import com.pbi.newsapp.utils.DataTest

@Composable
fun NewsDetails(
    modifier: Modifier = Modifier,
    article: Article
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.medium_padding))
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(id = R.dimen.vertical_padding),
                    horizontal = dimensionResource(id = R.dimen.horizontal_padding)
                )
        ) {
            NewsHeader(
                author = article.author ?: "none@news.api",
                publishedAt = article.publishedAt,
                maxLines = 1
            )
            NewsTitle(
                title = article.title
            )
            NewsImage(
                imageUrl = article.urlToImage
            )
            NewsDescription(
                newsDesc = article.description,
                maxLines = 3,
            )
            NewsFooter(
                source = article.source.name
            )
        }
    }
}

@Preview
@Composable
fun NewsApp() {
    val article = DataTest.article

    NewsAppTheme {
        // Content here
        NewsDetails(article = article)
    }
}