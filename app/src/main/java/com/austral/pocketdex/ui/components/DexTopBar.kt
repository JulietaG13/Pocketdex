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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.austral.pocketdex.R

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
                leftOption = stringResource(R.string.dex_screen_top_bar_toggle_found),
                rightOption = stringResource(R.string.dex_screen_top_bar_toggle_all),
                selected = showAll,
                onToggle = onToggle
            )
        }
    }
}
