package com.jsontodos.jsonplaceholdertodos.retrofit

import com.jsontodos.jsonplaceholdertodos.models.TodosData
import java.util.*
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("todos")
    suspend fun getTodos(): Response<ArrayList<TodosData>>
}