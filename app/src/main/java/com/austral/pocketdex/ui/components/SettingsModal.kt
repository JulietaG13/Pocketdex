package com.austral.pocketdex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.austral.pocketdex.R
import com.austral.pocketdex.ui.theme.Dimensions
import com.google.firebase.auth.FirebaseUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsModal(
    user: FirebaseUser?,
    onDismiss: () -> Unit,
    onDeleteAllProgress: () -> Unit,
    onSignOut: () -> Unit,
    onSignIn: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.LargePadding),
            shape = RoundedCornerShape(Dimensions.LargeRoundedCorner)
        ) {
            Column(
                modifier = Modifier.padding(Dimensions.LargePadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = stringResource(R.string.settings_modal_title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(Dimensions.LargeSpacer))
                
                if (user == null) {
                    GoogleLoginButton(
                        modifier = Modifier,
                        onClick = {
                            onSignIn()
                            onDismiss()
                        }
                    )
                } else {
                    TextButton(
                        onClick = {
                            onSignOut()
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(stringResource(R.string.settings_modal_sign_out_button))
                    }
                }
                
                Spacer(modifier = Modifier.height(Dimensions.SmallSpacer))
                TextButton(
                    onClick = {
                        onDeleteAllProgress()
                        onDismiss()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text(stringResource(R.string.settings_modal_delete_all_button))
                }
                
                Spacer(modifier = Modifier.height(Dimensions.MediumSpacer))
                
                // Close button
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onDismiss) {
                        Text(stringResource(R.string.settings_modal_close_button))
                    }
                }
            }
        }
    }
} 