package com.pbi.newsapp.ui.news.view.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.pbi.newsapp.R
import com.pbi.newsapp.domain.model.Article
import com.pbi.newsapp.ui.news.view.NewsViewState
import com.pbi.newsapp.utils.Endpoint

@Composable
fun NewsContent(
    newsPagingData: LazyPagingItems<Article>,
    headlinesPagingData: LazyPagingItems<Article>,
    state: NewsViewState,
    newsOnClick: () -> Unit,
    headlinesOnClick: () -> Unit,
) {
    val btnNewsFocusRequester = remember { FocusRequester() }
    val btnHeadlinesRequester = remember { FocusRequester() }

    var iconNews by remember {
        mutableIntStateOf(R.drawable.newspaper_solid)
    }
    var iconHeadlines by remember {
        mutableIntStateOf(R.drawable.fire_regular)
    }


    Scaffold(bottomBar = {
        BottomAppBar(
            containerColor = Color(0xFF1b1b1c),
            actions = {
                IconButton(onClick = {
                        newsOnClick()
                        btnNewsFocusRequester.requestFocus()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(btnNewsFocusRequester)
                        .onFocusChanged {
                            if (it.isFocused) {
                                iconNews = R.drawable.newspaper_solid
                                iconHeadlines = R.drawable.fire_regular
                            }
                        }
                        .focusTarget()
                ) {
                    Icon(
                        painterResource(id = iconNews),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
                IconButton(onClick = {
                        headlinesOnClick()
                        btnHeadlinesRequester.requestFocus()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(btnHeadlinesRequester)
                        .onFocusChanged {
                            if (it.isFocused) {
                                iconNews = R.drawable.newspaper_regular
                                iconHeadlines = R.drawable.fire_solid
                            }
                        }
                        .focusTarget(),
                ) {
                    Icon(
                        painterResource(id = iconHeadlines),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            },
            modifier = Modifier
                .height(50.dp)
                .padding(0.dp)
        )
    }) {
        when(state.endpoint) {
            Endpoint.EVERYTHING -> ArticleLazyColumn(paddingValues = it, articles = newsPagingData)
            else -> ArticleLazyColumn(paddingValues = it, articles = headlinesPagingData)
        }
    }
}