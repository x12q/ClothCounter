package com.clothcounter.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CounterLabel(
    text:String,
    modifier: Modifier = Modifier,
) {
    CommonBox(modifier){
        Text(text, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}


@Preview
@Composable
fun Preview_CounterLabel() {
    CounterLabel(text = "asdasdsadaaaaaaaaaaaaddddddddddddd")
}
