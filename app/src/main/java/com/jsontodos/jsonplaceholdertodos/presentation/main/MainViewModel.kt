package com.jsontodos.jsonplaceholdertodos.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsontodos.jsonplaceholdertodos.models.TodosData
import com.jsontodos.jsonplaceholdertodos.repository.TodosDataRepository
import com.jsontodos.jsonplaceholdertodos.retrofit.Result
import javax.inject.Inject
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(private val repository: TodosDataRepository) : ViewModel() {

    private val _todosData = MutableLiveData<Result<ArrayList<TodosData>>>()
    private var todosDataFromDb = MutableLiveData<ArrayList<TodosData>>()

    val todosDataLiveData: LiveData<Result<ArrayList<TodosData>>>
        get() = _todosData

    var selectedIndex = 0

    fun getTodosData() {
        viewModelScope.launch {
            val result = repository.getTodosRepoData()
            repository.insertAllData(result.data!!)
            _todosData.postValue(result)
        }
    }

    fun addTodosToDb(todosData: ArrayList<TodosData>) {
        viewModelScope.launch {
            repository.insertAllData(todosData)
        }
    }

    fun getAllTodosFromDb(): LiveData<List<TodosData>> {
        Log.e("TAG", "getAllTodosFromDb: "+ repository.getAllNotes() )

        return repository.getAllNotes()
    }

    fun updateTodosData(completed:Boolean, id:Int) {
        viewModelScope.launch {

            repository.updateTodo(completed, id)

        }
    }
}