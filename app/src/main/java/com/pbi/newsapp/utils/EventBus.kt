package com.pbi.newsapp.utils

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object EventBus {

    private val _events = Channel<Any>()
    val event = _events.receiveAsFlow()

    suspend fun sendEvent(event: Any) {
        _events.send(event)
    }

}

sealed interface Event {
    data class ToastMessage(val message: String)
}