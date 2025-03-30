package com.austral.pocketdex.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun DexTopBar(
    showAll: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var chipHeight by remember { mutableStateOf(0.dp) }
    val currentLocalDensity = LocalDensity.current

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    chipHeight = with(currentLocalDensity) { coordinates.size.height.toDp() }
                },
            contentAlignment = Alignment.Center
        ) {
            ToggleChip(
                selected = showAll,
                onToggle = onToggle
            )
        }
    }
}
