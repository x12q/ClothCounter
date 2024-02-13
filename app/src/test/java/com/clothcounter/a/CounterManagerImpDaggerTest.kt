package com.clothcounter.a

import com.clothcounter.di.DaggerTestComponent
import com.clothcounter.di.TestComponent
import org.junit.Before
import org.junit.Test


class CounterManagerImpDaggerTest{

    lateinit var counterManager:CounterManager
    lateinit var comp: TestComponent

    @Before
    fun bf(){
        comp = DaggerTestComponent.create()
        counterManager = comp.counterManager
    }

    @Test
    fun addition_isCorrect() {
        println(counterManager)
    }
}
