package com.clothcounter.di

import androidx.datastore.core.DataStore
import com.clothcounter.CounterListProto
import com.clothcounter.a.MockDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

/**
 *
 */
@Module
@DisableInstallInCheck
interface FakeForDirectMocking_Module {

    @Binds
    @Singleton
    fun pDataStore(i: MockDataStore):DataStore<CounterListProto>

}


