package com.austral.pocketdex.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.austral.pocketdex.ui.components.Sprite
import kotlin.random.Random

@Composable
fun GuessScreen() {
    var text by remember { mutableStateOf("abc 123") }
    var id by remember { mutableIntStateOf(Random.nextInt(1, 1025+1)) }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Sprite(
            id = id, //Random.nextInt(1, 1025+1),
            hidden = true
        )

        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            //label = { Text("???") },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center),
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = ".......", style = MaterialTheme.typography.displayMedium)
    }
}