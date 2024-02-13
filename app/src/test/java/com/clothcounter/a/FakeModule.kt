package com.clothcounter.a

import androidx.datastore.core.DataStore
import com.clothcounter.CounterListProto
import com.clothcounter.di.Fake
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Introduce fake object into Hilt's DI graph
 */
@Module
@InstallIn(SingletonComponent::class)
interface FakeModule {

    @Binds
    @Singleton
    @Fake
    fun pDataStore(i: MockDataStore): DataStore<CounterListProto>

}
