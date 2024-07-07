package com.clothcounter.a

import kotlinx.coroutines.flow.StateFlow

/**
 * Handle reading, saving, querying counters
 */
interface CounterManager {
    val counterFlow: StateFlow<Map<String, Counter>>
    suspend fun addCounter(counter: Counter)
    suspend fun updateCounter(newCounter:Counter)
    suspend fun deleteCounter(counter:Counter)
    val sum:Float
    suspend fun resetAll()
}
