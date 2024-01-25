package com.pbi.newsapp.data.mapper

import android.util.Log
import com.pbi.newsapp.domain.model.ApiError
import com.pbi.newsapp.domain.model.NetworkError
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toNetworkError() : NetworkError {
    val error = when(this) {
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> {
            Log.e("network-error", this.stackTraceToString() )
            ApiError.UnknownError
        }
    }

    return NetworkError(
        error = error,
        t = this
    )
}