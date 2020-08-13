package com.devm7mdibrahim.mihrabi.utils

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
