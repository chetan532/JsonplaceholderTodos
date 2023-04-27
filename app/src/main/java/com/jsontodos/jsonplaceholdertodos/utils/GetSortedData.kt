package com.jsontodos.jsonplaceholdertodos.utils

import com.jsontodos.jsonplaceholdertodos.models.TodosData

class GetSortedData(private val nasaData: ArrayList<TodosData>) {
    operator fun invoke(): List<TodosData> {
        return nasaData.sortedByDescending { it.completed }
    }
}