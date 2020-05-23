package com.example.android.todolist.util

sealed class Result<T>(
    val data: T? = null,
    val error: String? = null
) {

    class Success<T>(data: T) : Result<T>(data)

    class Error<T>(error: String, data: T? = null) : Result<T>(data, error)

    class Loading<T> : Result<T>()

}