package com.austral.pocketdex.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.austral.pocketdex.ui.theme.Dimensions

@Composable
fun GoogleLoginButton(
    onClick: () -> Unit,
    modifier: Modifier
) {
    GoogleButtonUI(
        modifier = modifier,
        onClick = onClick,
    )
}

@Composable
private fun GoogleButtonUI(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(Dimensions.LargeRoundedCorner),
        border = BorderStroke(Dimensions.borderStroke, MaterialTheme.colorScheme.outline),
        contentPadding = PaddingValues(
            horizontal = Dimensions.LargePadding,
            vertical = Dimensions.SmallPadding
        )
    ) {
        Icon(
            imageVector = Icons.Rounded.Person,
            contentDescription = "Google Sign in"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Continue with Google")
    }
}