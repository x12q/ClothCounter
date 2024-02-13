package com.clothcounter.di

import com.clothcounter.a.CounterManager
import com.clothcounter.a.di.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class, FakeForDirectMocking_Module::class
])
interface TestComponent{
    val counterManager: CounterManager
}
