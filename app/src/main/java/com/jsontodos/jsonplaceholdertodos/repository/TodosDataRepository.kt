package com.jsontodos.jsonplaceholdertodos.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.jsontodos.jsonplaceholdertodos.db.TodoDatabase
import com.jsontodos.jsonplaceholdertodos.models.TodosData
import com.jsontodos.jsonplaceholdertodos.retrofit.Api
import com.jsontodos.jsonplaceholdertodos.retrofit.Result
import javax.inject.Inject

class TodosDataRepository @Inject constructor(private val api: Api,private val todoDatabase: TodoDatabase) {

    suspend fun getTodosRepoData(): Result<ArrayList<TodosData>> {
        val result = api.getTodos()

        return if (result.isSuccessful) {
            val responseBody = result.body()

            if (responseBody != null) {
                Result.Success(result.body())
            } else {
                Result.Error("API Error")
            }
        } else {
            Result.Error("API Error")
        }
    }

    suspend fun insertAllData(todosData: ArrayList<TodosData>) = todoDatabase.getTodoDao().insert(todosData)

    suspend fun updateTodo(completed:Boolean, id:Int) = todoDatabase.getTodoDao().updateNote(completed, id)

    suspend fun deleteNote(todosData:TodosData) = todoDatabase.getTodoDao().deleteSingleData(todosData)

    suspend fun clearAllData() = todoDatabase.getTodoDao().deleteAll()

    fun getAllNotes(): LiveData<List<TodosData>> = todoDatabase.getTodoDao().getTodosData()
}