package com.jsontodos.jsonplaceholdertodos.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jsontodos.jsonplaceholdertodos.repository.TodosDataRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: TodosDataRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}