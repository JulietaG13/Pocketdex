package com.austral.pocketdex.ui.components

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
    val biometricManager = remember { BiometricManager.from(context) }

    val isBiometricAvailable = remember {
        biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
    }

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
                when (isBiometricAvailable) {
                    BiometricManager.BIOMETRIC_SUCCESS -> {
                        // do nothing, all good :)
                    }

                    BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                        // No biometric features available on this device
                        Text(text = stringResource(R.string.biometric_error_no_hardware))
                    }

                    BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                        // Biometric features are currently unavailable.
                        Text(text = stringResource(R.string.biometric_error_unavailable))
                    }

                    BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                        // Biometric features available but a security vulnerability has been discovered
                        Text(text = stringResource(R.string.biometric_error_security_update_required))
                    }

                    BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                        // Biometric features are currently unavailable because the specified options are incompatible with the current Android version..
                        Text(text = stringResource(R.string.biometric_error_unsupported))
                    }

                    BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                        // Unable to determine whether the user can authenticate using biometrics
                        Text(text = stringResource(R.string.biometric_error_status_unknown))
                    }

                    BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                        // The user can't authenticate because no biometric or device credential is enrolled.
                        Text(text = stringResource(R.string.biometric_error_none_enrolled))
                    }
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