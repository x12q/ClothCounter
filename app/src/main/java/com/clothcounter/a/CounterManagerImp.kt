package com.clothcounter.a

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import com.clothcounter.CounterListProto
import com.clothcounter.a.Counter.Companion.toModel
import com.clothcounter.a.Counter.Companion.toProto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CounterManagerImp @Inject constructor(
    private val dataStore: DataStore<CounterListProto>,
) : CounterManager {

    private val counterListFlow: MutableStateFlow<Map<String, Counter>> =
        MutableStateFlow(emptyMap())

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    private val counterMapState: MutableState<Map<String, Counter>> =
        mutableStateOf(counterListFlow.value)

    override val allCounters: List<Counter> get() = counterMapState.value.values.toList()

    private val counterMap get() = counterMapState.value

    init {
        coroutineScope.launch {
            counterListFlow.collect {
                counterMapState.value = it
            }
        }
    }

    override fun loadCountersFromPersistence() {
        runBlocking {
            val data = dataStore.data.first().toModel().associateBy { it.key }
            counterListFlow.update { data }
        }
    }

    override fun saveCounter() {
        runBlocking {
            dataStore.updateData {
                allCounters.toProto()
            }
        }
    }


    override fun addCounter(i: Counter) {
        counterListFlow.value = counterListFlow.value + (i.key to i)
    }

    override fun updateCounter(newCounter: Counter) {
        val newMap = counterMap.toMutableMap()
        newMap[newCounter.key] = newCounter
        update(newMap)
    }

    override fun deleteCounter(i: Counter) {
        val newMap = counterMap - i.key
        update(newMap)
    }

    override val sum: Float
        get() = allCounters.map { it.sumValue }.sum()

    private fun update(newMap:Map<String,Counter>){
        runBlocking {
            dataStore.updateData {
                newMap.values.toList().toProto()
            }
        }
        counterListFlow.update {
            newMap
        }
    }
}
