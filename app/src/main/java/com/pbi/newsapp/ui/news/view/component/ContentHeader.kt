package com.pbi.newsapp.ui.news.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pbi.newsapp.R
import com.pbi.newsapp.ui.util.component.helveticaFamily


@Composable
fun NewsHeader(
    author: String,
    publishedAt: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.vertical_padding)
            ),
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier
                .size(
                    dimensionResource(id = R.dimen.header_icon_size)
                )
                .padding(
                    end = dimensionResource(id = R.dimen.header_icon_padding)
                )
        )

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = author,
                fontFamily = helveticaFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
            )

            Spacer(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(id = R.dimen.header_spacer_padding))
            )

            Text(
                text = publishedAt,
                fontFamily = helveticaFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp
            )
        }
    }

}