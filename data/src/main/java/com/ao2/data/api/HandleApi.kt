package com.ao2.data.api

import com.ao2.domain.NetworkResult

internal inline fun <T> handleApi(transform: () -> T): NetworkResult<T> = try {
    NetworkResult.Success(transform.invoke())
} catch (e: Exception) {
    when (e) {
        else -> NetworkResult.Error(e)
    }
}