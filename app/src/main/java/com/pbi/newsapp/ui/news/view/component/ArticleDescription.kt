package com.pbi.newsapp.ui.news.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.pbi.newsapp.R
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.ui.theme.articleBackground

@Composable
fun NewsDetails(
    modifier: Modifier = Modifier,
    article: Article
) {
    Column(
        modifier = modifier
            .background(articleBackground)
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