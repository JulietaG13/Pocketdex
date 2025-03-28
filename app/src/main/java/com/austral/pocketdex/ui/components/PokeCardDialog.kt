package com.austral.pocketdex.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.austral.pocketdex.ui.theme.Dimensions

@Composable
fun PokeCardDialog(
    id: Int,
    name: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onDismiss),
            contentAlignment = Alignment.Center,
        ) {
            Card(
                shape = RoundedCornerShape(Dimensions.RoundedCorner),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(Dimensions.CardBorder, Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimensions.LargePadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.6f)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp)),
                    ) {
                        Sprite(
                            id = id,
                            modifier = Modifier
                                .fillMaxSize(0.8f)
                                .padding(Dimensions.LargePadding)
                        )
                    }
                    Text(text = name, style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}