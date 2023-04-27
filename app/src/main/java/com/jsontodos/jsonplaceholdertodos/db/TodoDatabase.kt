package com.jsontodos.jsonplaceholdertodos.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsontodos.jsonplaceholdertodos.models.TodosData


@Database(entities = [TodosData::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
}