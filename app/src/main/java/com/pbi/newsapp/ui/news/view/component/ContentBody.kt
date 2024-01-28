package com.pbi.newsapp.ui.news.view.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pbi.newsapp.R
import com.pbi.newsapp.ui.util.component.helveticaFamily

@Composable
fun NewsImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    imageModifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        contentAlignment = Alignment.Center,
    ) {
        Log.i("image-url", imageUrl ?: "null")
        if (imageUrl != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = imageModifier.size(
                    width = 300.dp,
                    height = 200.dp
                )
            )

        } else {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier.size(
                    width = 300.dp,
                    height = 200.dp
                )
            )
        }
    }
}

@Composable
fun NewsTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        fontFamily = helveticaFamily,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(vertical = 5.dp)
    )
}

@Composable
fun NewsDescription(
    newsDesc: String?,
    maxLines: Int,
    modifier: Modifier = Modifier
) {
    if(newsDesc != null)
        Text(
            text = newsDesc,
            textAlign = TextAlign.Justify,
            fontFamily = helveticaFamily,
            fontWeight = FontWeight.Normal,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(vertical = 5.dp)
        )
}