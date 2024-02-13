package com.clothcounter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clothcounter.a.Counter

@Composable
fun CounterItem(
    counter: Counter,
    onUp:(newCounter:Counter) -> Unit,
    onDown:(newCounter:Counter) -> Unit,
    onDelete:(Counter)->Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CounterButton(
            text = "X",
            onClick = {
                onDelete(counter)
            }
        )
        CounterLabel(
            text = counter.text,
            modifier = Modifier
                .width(0.dp)
                .weight(1f)
        )
        NumberBox(counter.count)
        CounterButton(
            text = "-",
            onClick = {
                val newCounter = counter.decreaseOne()
                onUp(newCounter)
            }
        )
        CounterButton(
            text = "+",
            onClick = {
                val newCounter = counter.increaseOne()
                onDown(newCounter)
            }
        )
    }
}


@Preview
@Composable
fun Preview_CounterItem() {
    CounterItem(counter = Counter.random(), onUp = {}, onDown = {}, onDelete = {})
}



