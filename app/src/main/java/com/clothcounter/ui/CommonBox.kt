package com.clothcounter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun CommonBox(modifier: Modifier=Modifier,content:@Composable ()->Unit) {
    Box(
        modifier
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp,Color.Black,RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(10.dp)

    ) {
        content()
    }
}


@Preview
@Composable
fun Preview_CommonBox() {
    CommonBox {
        Text("abc")
    }
}
