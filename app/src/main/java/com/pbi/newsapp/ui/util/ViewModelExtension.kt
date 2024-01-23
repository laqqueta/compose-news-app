package com.pbi.newsapp.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbi.newsapp.utils.EventBus
import kotlinx.coroutines.launch

fun ViewModel.sendEvent(event: Any) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}