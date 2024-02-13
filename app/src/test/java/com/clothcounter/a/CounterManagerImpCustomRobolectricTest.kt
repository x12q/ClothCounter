package com.clothcounter.a

import androidx.datastore.core.DataStore
import com.clothcounter.CounterListProto
import com.clothcounter.di.Fake
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
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
class CounterManagerImpCustomRobolectricTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var realSubject:CounterManagerImp

    lateinit var fakeSubject:CounterManagerImp

    @Inject
    lateinit var data: DataStore<CounterListProto>

    @Inject
    @Fake
    lateinit var fakeData: DataStore<CounterListProto>

    @Before
    fun bf(){
        hiltRule.inject()
        fakeSubject = CounterManagerImp(
            dataStore = fakeData
        )
    }

    @Test
    fun qweqwe(){
        println(fakeData)
        println(fakeSubject)
        println(realSubject)
    }
}

