package com.clothcounter.a

import androidx.datastore.core.DataStore
import com.clothcounter.CounterListProto
import com.clothcounter.CounterListProtoOuterClass
import com.clothcounter.a.Counter
import com.clothcounter.a.Counter.Companion.toProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MockDataStore @Inject constructor() : DataStore<CounterListProto> {
    val mockData = List(10){
        Counter.random()
    }.toProto()

    private val f = MutableStateFlow(mockData)

    override val data: Flow<CounterListProto> = f

    override suspend fun updateData(transform: suspend (t: CounterListProto) ->
    CounterListProto): CounterListProto {
        val newData = transform(data.first())
        f.update { newData }
        return newData
    }
}
