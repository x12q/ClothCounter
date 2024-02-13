package com.clothcounter.ui

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NumberBox(
    i:Int,
    modifier: Modifier = Modifier,
) {
    CommonBox(modifier) {
        Text(text = "$i")
    }
}


@Preview
@Composable
fun Preview_NumberBox() {
    NumberBox(i = 10)
}
