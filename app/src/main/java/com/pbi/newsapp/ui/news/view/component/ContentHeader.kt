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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = modifier.padding(vertical = 5.dp),
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 3.dp)
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
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            Text(
                text = publishedAt,
                fontFamily = helveticaFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp
            )
        }
    }

}