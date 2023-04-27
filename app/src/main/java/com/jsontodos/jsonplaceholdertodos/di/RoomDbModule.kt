package com.jsontodos.jsonplaceholdertodos.di

import android.app.Application
import androidx.room.Room
import com.jsontodos.jsonplaceholdertodos.db.TodoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDbModule(application: Application) {


    private var mainApplication = application
    private lateinit var todoDatabase: TodoDatabase

    @Singleton
    @Provides
    fun providesRoomDatabase(): TodoDatabase {
        todoDatabase =
            Room.databaseBuilder(mainApplication, TodoDatabase::class.java, "todo_database.db")
                .fallbackToDestructiveMigration()
                .build()
        return todoDatabase
    }

    @Singleton
    @Provides
    fun getTodoDao(todoDatabase: TodoDatabase) = todoDatabase.getTodoDao()

}