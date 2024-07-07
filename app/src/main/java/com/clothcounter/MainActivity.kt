package com.clothcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.clothcounter.a.Counter
import com.clothcounter.a.CounterManager
import com.clothcounter.ui.AddPanel
import com.clothcounter.ui.CounterItem
import com.clothcounter.ui.theme.ClothCounterTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var manager: CounterManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val list = manager.counterFlow.collectAsState().value.values
            val cr = rememberCoroutineScope()
            ClothCounterTheme {
                Column {
                    Button(onClick = {
                        cr.launch {
                            manager.resetAll()
                        }
                    }) {
                        Text("Reset")
                    }
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(0.dp)
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        for (counter in list) {
                            CounterItem(
                                counter = counter,
                                onUp = {
                                    cr.launch {
                                        manager.updateCounter(it)
                                    }
                                },
                                onDown = {
                                    cr.launch {
                                        manager.updateCounter(it)
                                    }
                                },
                                onDelete = {
                                    cr.launch {
                                        manager.deleteCounter(it)
                                    }
                                }
                            )
                        }
                    }

                    Divider()

                    AddPanel(sum = manager.sum, onClick = { text, sumFactor ->
                        cr.launch {
                            manager.addCounter(
                                Counter(
                                    key = UUID.randomUUID().toString(),
                                    text = text,
                                    count = 0,
                                    sumFactor = sumFactor
                                )
                            )

                        }
                    })
                }
            }
        }
    }
}
