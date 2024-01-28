package com.pbi.newsapp.ui.news.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pbi.newsapp.domain.model.Article

@Composable
fun NewsDetails(
    modifier: Modifier = Modifier,
    article: Article
) {
    Column(
        modifier = modifier
            .background(Color(0xFF202021))
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
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