package com.jsontodos.jsonplaceholdertodos

import android.app.Application
import com.jsontodos.jsonplaceholdertodos.di.ApplicationComponent
import com.jsontodos.jsonplaceholdertodos.di.DaggerApplicationComponent
import com.jsontodos.jsonplaceholdertodos.di.RoomDbModule

class MainApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent =
            DaggerApplicationComponent.builder().roomDbModule(RoomDbModule(this))
                .build()
    }
}