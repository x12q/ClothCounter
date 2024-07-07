package com.clothcounter.a.di

import com.clothcounter.a.AppCoroutineProvider
import com.clothcounter.a.AppCoroutineProviderImp
import com.clothcounter.a.CounterManager
import com.clothcounter.a.CounterManagerImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    @Singleton
    fun counterManager (i:CounterManagerImp): CounterManager

    @Binds
    @Singleton
    fun coroutineProvider(i:AppCoroutineProviderImp): AppCoroutineProvider
}
