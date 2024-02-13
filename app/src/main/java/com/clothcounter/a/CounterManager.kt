package com.clothcounter.a

/**
 * Handle reading, saving, querying counters
 */
interface CounterManager {
    val allCounters:List<Counter>
    fun loadCountersFromPersistence()
    fun saveCounter()
    fun addCounter(i: Counter)
    fun updateCounter(newCounter:Counter)
    fun deleteCounter(i:Counter)
    val sum:Float
}
