package com.austral.pocketdex.ui.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun AutoResizeText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle,
    textAlign: TextAlign = TextAlign.Center,
    contentAlignment: Alignment = Alignment.Center,
    maxLines: Int = Int.MAX_VALUE,
    minFontSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize,
    maxFontSize: TextUnit = MaterialTheme.typography.titleLarge.fontSize
) {
    var textSize by remember { mutableStateOf(maxFontSize) }
    var readyToDraw by remember { mutableStateOf(false) }

    BoxWithConstraints(
        contentAlignment = contentAlignment,
        modifier = modifier
    ) {
        val constraints = this.constraints

        Text(
            text = text,
            style = style.copy(fontSize = textSize),
            textAlign = textAlign,
            maxLines = maxLines,
            overflow = TextOverflow.Visible,
            modifier = Modifier.alpha(if (readyToDraw) 1f else 0f),
            onTextLayout = { result ->
                if ((result.didOverflowHeight || result.didOverflowWidth) && textSize > minFontSize) {
                    textSize *= 0.95f
                } else {
                    readyToDraw = true
                }
            }
        )
    }
}
