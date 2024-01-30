package com.pbi.newsapp.utils

suspend fun sendEvent(event: Any) {
    EventBus.sendEvent(event)
}