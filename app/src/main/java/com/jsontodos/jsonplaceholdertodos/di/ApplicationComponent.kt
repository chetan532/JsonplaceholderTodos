package com.jsontodos.jsonplaceholdertodos.di

import com.jsontodos.jsonplaceholdertodos.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RoomDbModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
}