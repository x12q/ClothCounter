package com.clothcounter.ui

import android.media.metrics.TrackChangeEvent
import android.view.LayoutInflater
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material3.Button
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.clothcounter.R
import com.google.android.material.slider.Slider
import kotlin.math.roundToInt


@Composable
fun Track(width: Dp,cut:Int) {
    Box(modifier = Modifier
        .width(width)
        .height(30.dp)
        .background(Color.Blue))
}

@Composable
fun Thumb(modifier: Modifier=Modifier){
    Box(modifier = modifier
        .size(40.dp)
        .background(Color.Red))
}


@Composable
fun ZSlider(
    modifier: Modifier = Modifier,
) {

    var offsetMs by remember {
       mutableStateOf(0f)
    }
//    val offsetX by animateDpAsState(targetValue = offsetMs)

    val cut = 3
    Column {
        Box{
            Track(width = 500.dp,cut)
            Thumb(modifier = Modifier
                .offset { IntOffset(x=offsetMs.roundToInt(), y = 0) }
                .pointerInput(Unit){
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetMs+=dragAmount.x
                }
            })
        }

    }
}
@Composable
@Preview
private fun DraggableTextLowLevel() {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        Box(
            Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .background(Color.Blue)
                .size(50.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        )
    }
}
@Preview
@Composable
fun Bench() {
    ZSlider()
}

@Preview
@Composable
fun SliderAdvancedExample() {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
//            colors = SliderDefaults.colors(
//                thumbColor = MaterialTheme.colorScheme.secondary,
//                activeTrackColor = MaterialTheme.colorScheme.secondary,
//                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
//            ),
            steps = 3,
            valueRange = 0f..50f
        )
        Text(text = sliderPosition.toString())
    }
}
