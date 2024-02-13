package com.clothcounter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddPanel(
    sum:Float,
    onClick: (String, Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var t by remember { mutableStateOf("") }
    var n by remember { mutableStateOf("1") }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text(text = "Total: ${sum}")

        Row(modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            CommonBox(modifier = Modifier
                .width(0.dp)
                .weight(1f))
            {
                BasicTextField(
                    value = t,
                    onValueChange = {
                        t = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {keyboardController?.hide()})
                )
            }
            CommonBox(modifier = Modifier
                .width(0.dp)
                .weight(1f))
            {
                BasicTextField(
                    value = "${n}",
                    onValueChange = {
                        n = it
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {keyboardController?.hide()})
                )
            }


            Button(onClick = {
                if(t.isNotEmpty()){
                    val num = n.toFloatOrNull()
                    if(num != null){
                        onClick(t,num)
                        t = ""
                        n = "1"
                    }
                }
            }) {
                Text("Add")
            }
        }
    }
}


@Preview
@Composable
fun Preview_AddPanel() {
    AddPanel(sum = 3.4f,onClick = {_,_->})
}
