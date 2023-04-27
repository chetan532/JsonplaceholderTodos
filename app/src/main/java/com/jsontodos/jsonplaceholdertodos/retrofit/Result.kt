package com.jsontodos.jsonplaceholdertodos.retrofit

sealed class Result<T>(val data: T? = null, val errorMessage: String? = null) {

    class Success<T>(data: T? = null) : Result<T>(data = data)
    class Error<T>(errorMessage: String) : Result<T>(errorMessage = errorMessage)
    class Loading<T> : Result<T>()
}