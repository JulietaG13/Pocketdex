package com.austral.pocketdex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.austral.pocketdex.ui.theme.Dimensions

@Composable
fun ToggleChip(
    leftOption: String,
    rightOption: String,
    selected: Boolean,
    onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = Dimensions.LargeRoundedCorner, bottomEnd = Dimensions.LargeRoundedCorner))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(Dimensions.SmallPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val selectedColor = MaterialTheme.colorScheme.primary
        val unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant


        TextButton(
            onClick = { onToggle(false) },
            colors = ButtonDefaults.textButtonColors(
                containerColor = if (!selected) selectedColor else Color.Transparent,
                contentColor = if (!selected) Color.White else unselectedColor
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(leftOption)
        }

        TextButton(
            onClick = { onToggle(true) },
            colors = ButtonDefaults.textButtonColors(
                containerColor = if (selected) selectedColor else Color.Transparent,
                contentColor = if (selected) Color.White else unselectedColor
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(rightOption)
        }
    }
}
