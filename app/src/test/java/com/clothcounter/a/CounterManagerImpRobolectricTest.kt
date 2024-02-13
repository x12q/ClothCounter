package com.clothcounter.a

import androidx.datastore.core.DataStore
import com.clothcounter.CounterListProto
import com.clothcounter.a.Counter.Companion.toModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class CounterManagerImpRobolectricTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var cm:CounterManagerImp

    @Inject
    lateinit var data:DataStore<CounterListProto>

    @Before
    fun bf() {
        hiltRule.inject()
    }

    @Test
    fun addCounter(){

        val c1 = cm.allCounters.size

        val dataStoreSize = runBlocking {
            data.data.first().counterList.size
        }

        c1 shouldBe dataStoreSize

        val newCounter = Counter.random()

        cm.addCounter(newCounter)
        cm.saveCounter()

        val dataStoreSize1 = runBlocking {
            data.data.first().counterList.size
        }

        dataStoreSize1 shouldBe 1
        cm.allCounters.size shouldBeExactly 1

        runBlocking {
            val counterFromDataStore = data.data.first().counterList.map { it.toModel() }.first()
            counterFromDataStore shouldBe newCounter
        }
    }
}
