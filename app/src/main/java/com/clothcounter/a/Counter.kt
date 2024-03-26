package com.clothcounter.a

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.clothcounter.CounterListProto
import com.clothcounter.CounterProto
import com.clothcounter.a.di.AppModule
import dagger.Component
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

data class Counter(
    val key: String,
    val text: String,
    val count: Int,
    val sumFactor:Float,
) {
    val sumValue:Float get() = count*sumFactor

    fun increaseOne(): Counter {
        return this.copy(count = count + 1)
    }

    fun decreaseOne(): Counter {
        return this.copy(count = maxOf(0,count - 1))
    }

    fun toProto(): CounterProto {
        return CounterProto.newBuilder().apply {
            key = this@Counter.key
            text = this@Counter.text
            count = this@Counter.count
            sumFactor = this@Counter.sumFactor
        }.build()
    }

    object CounterSerializer : Serializer<CounterProto> {
        override val defaultValue: CounterProto
            get() = CounterProto.getDefaultInstance()

        override suspend fun readFrom(input: InputStream): CounterProto {
            return CounterProto.parseFrom(input)
        }

        override suspend fun writeTo(t: CounterProto, output: OutputStream) {
            t.writeTo(output)
        }
    }

    object CounterListSerializer : Serializer<CounterListProto> {
        override val defaultValue: CounterListProto
            get() = CounterListProto.getDefaultInstance()

        override suspend fun readFrom(input: InputStream): CounterListProto {
            return CounterListProto.parseFrom(input)
        }

        override suspend fun writeTo(t: CounterListProto, output: OutputStream) {
            t.writeTo(output)
        }

    }

    companion object {
        fun random(): Counter {
            return Counter(
                key = "key: " + UUID.randomUUID().toString(),
                text = "text: " + UUID.randomUUID().toString(),
                count = (1..100).random(),
                sumFactor = 1f
            )
        }


        fun CounterProto.toModel(): Counter {
            return Counter(
                key = this.key,
                text = this.text,
                count = this.count,
                sumFactor = this.sumFactor,
            )
        }

        fun CounterListProto.toModel(): List<Counter> {
            return this.counterList.map {
                it.toModel()
            }
        }

        fun List<Counter>.toProto(): CounterListProto {
            return CounterListProto
                .newBuilder()
                .addAllCounter(this.map { it.toProto() })
                .build()
        }

        val Context.counterDataStore: DataStore<CounterListProto> by dataStore(
            fileName = "counters.pb",
            serializer = CounterListSerializer
        )
    }
}
