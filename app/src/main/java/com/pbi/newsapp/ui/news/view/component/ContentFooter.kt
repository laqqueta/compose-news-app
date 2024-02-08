package com.pbi.newsapp.ui.news.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.pbi.newsapp.R

@Composable
fun NewsFooter(
    source: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.vertical_padding)
            )
    ) {
        Text(
            text = "Source: $source",
            style = MaterialTheme.typography.displaySmall
        )
    }
}