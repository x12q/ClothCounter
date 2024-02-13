package com.clothcounter.a

import com.clothcounter.a.Counter
import org.junit.Test
import io.kotest.matchers.shouldBe
class CounterDataTest {


    @Test
    fun increaseOne() {
        val c0 = Counter.random()
        val c1 = c0.increaseOne()
        c1.count shouldBe (c0.count+1)
    }

    @Test
    fun decreaseOne() {
        val c0 = Counter.random()
        val c1 = c0.decreaseOne()
        c1.count shouldBe (c0.count-1)
    }
}
