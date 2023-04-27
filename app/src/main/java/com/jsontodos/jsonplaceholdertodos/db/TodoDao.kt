package com.jsontodos.jsonplaceholdertodos.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.jsontodos.jsonplaceholdertodos.models.TodosData


@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cats: ArrayList<TodosData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE) //if some data is same/conflict, it'll be replace with new data.
    suspend fun insertSingleTodo(todosData: TodosData)

    @Query("SELECT * FROM todoItems")
    fun getTodosData(): LiveData<List<TodosData>>

    @Query("DELETE FROM todoItems")
    suspend fun deleteAll()

    @Query("UPDATE todoItems SET completed=:completed WHERE id = :id")
    suspend fun updateNote(completed:Boolean, id:Int)

    @Delete
    suspend fun deleteSingleData(todosData: TodosData)
}