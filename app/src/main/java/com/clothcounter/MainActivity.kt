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
import androidx.compose.material3.Divider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.clothcounter.a.Counter
import com.clothcounter.a.CounterManager
import com.clothcounter.ui.AddPanel
import com.clothcounter.ui.CounterItem
import com.clothcounter.ui.theme.ClothCounterTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var manager: CounterManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        manager.loadCountersFromPersistence()
        setContent {
            val list = manager.allCounters
            ClothCounterTheme {
                Column {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(0.dp)
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                        ,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        for (counter in list) {
                            CounterItem(
                                counter = counter,
                                onUp = {
                                    manager.updateCounter(it)
                                },
                                onDown = {
                                    manager.updateCounter(it)
                                },
                                onDelete = {
                                    manager.deleteCounter(it)
                                }
                            )
                        }
                    }

                    Divider()

                    AddPanel(sum = manager.sum ,onClick = {text,sumFactor->
                        manager.addCounter(
                            Counter(
                                key = UUID.randomUUID().toString(),
                                text = text,
                                count = 0,
                                sumFactor = sumFactor
                            )
                        )
                    })
                }
            }
        }
    }
}
