package com.pbi.newsapp.ui.news.view.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.pbi.newsapp.R
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.ui.news.view.NewsScreen
import com.pbi.newsapp.ui.theme.NewsAppTheme
import com.pbi.newsapp.ui.theme.articleBackground
import com.pbi.newsapp.utils.DataTest

@Composable
fun NewsDetails(
    modifier: Modifier = Modifier,
    article: Article
) {
    Column(
        modifier = modifier
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(id = R.dimen.vertical_padding),
                    horizontal = dimensionResource(id = R.dimen.horizontal_padding)
                )
        ) {
            NewsHeader(
                author = article.author,
                publishedAt = article.publishedAt
            )
            NewsTitle(
                title = article.title
            )
            NewsImage(
                imageUrl = article.urlToImage
            )
            NewsDescription(
                newsDesc = article.description,
                maxLines = 4
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

    NewsAppTheme(darkTheme = false) {
        // Content here
        NewsDetails(article = article)
    }
}