package com.pbi.newsapp

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.pbi.newsapp.ui.news.view.NewsScreen
import com.pbi.newsapp.ui.theme.NewsAppTheme
import com.pbi.newsapp.utils.Event
import com.pbi.newsapp.utils.EventBus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val lifecycle = LocalLifecycleOwner.current.lifecycle
                LaunchedEffect(key1 = lifecycle) {
                    repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                        EventBus.event.collect {event ->
                            if (event is Event.ToastMessage) {
                                Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsScreen()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_2_XL
)
@Composable
fun NewsApp() {
    NewsAppTheme {

            // Content here
            NewsScreen()

    }
}