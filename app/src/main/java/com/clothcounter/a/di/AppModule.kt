package com.clothcounter.a.di

import com.clothcounter.a.CounterManager
import com.clothcounter.a.CounterManagerImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    @Singleton
    fun qwe (i:CounterManagerImp): CounterManager
}
