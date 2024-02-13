package com.clothcounter.a.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.clothcounter.CounterListProto
import com.clothcounter.CounterListProtoOuterClass
import com.clothcounter.a.Counter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AndroidRelatedModule {

    companion object{

        val Context.counterDataStore: DataStore<CounterListProto> by dataStore(
            fileName = "counters.pb",
            serializer = Counter.CounterListSerializer
        )

        @Provides
        @Singleton
        fun pdataStore(@ApplicationContext context: Context): DataStore<CounterListProto> {
            return context.counterDataStore
        }
    }
}
