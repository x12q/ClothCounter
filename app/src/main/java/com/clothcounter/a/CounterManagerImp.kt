package com.clothcounter.a

import androidx.datastore.core.DataStore
import com.clothcounter.CounterListProto
import com.clothcounter.a.Counter.Companion.toModel
import com.clothcounter.a.Counter.Companion.toProto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class CounterManagerImp @Inject constructor(
    private val dataStore: DataStore<CounterListProto>,
    private val coroutineProvider: AppCoroutineProvider
) : CounterManager {

    private val coroutineScope: CoroutineScope = coroutineProvider.backgroundCoroutineScope

    override val counterFlow: StateFlow<Map<String, Counter>> =
        dataStore.data.map { counterListProto ->
            counterListProto.toModel().associateBy { i -> i.key }
        }.stateIn(coroutineScope, SharingStarted.Eagerly, emptyMap())

    val allCounters: List<Counter> get() = counterFlow.value.values.toList()

    private val counterMap get() = counterFlow.value

    override suspend fun addCounter(i: Counter) {
        val newCounters = allCounters + i
        dataStore.updateData {
            newCounters.toProto()
        }
    }

    override suspend fun updateCounter(newCounter: Counter) {
        val newMap = counterMap.toMutableMap()
        newMap[newCounter.key] = newCounter
        update(newMap)
    }

    override suspend fun deleteCounter(i: Counter) {
        val newMap = counterMap - i.key
        update(newMap)
    }

    override val sum: Float
        get() = allCounters.map { it.sumValue }.sum()

    override suspend fun resetAll() {
        val newCounters = allCounters.map { counter -> counter.reset() }
        dataStore.updateData {
            newCounters.toProto()
        }
    }

    private suspend fun update(newMap: Map<String, Counter>) {

        dataStore.updateData {
            newMap.values.toList().toProto()
        }

    }
}
