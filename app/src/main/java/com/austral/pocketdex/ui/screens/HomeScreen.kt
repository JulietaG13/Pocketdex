package com.austral.pocketdex.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.austral.pocketdex.R
import com.austral.pocketdex.data.model.Pokemon
import com.austral.pocketdex.ui.components.GoogleLoginButton
import com.austral.pocketdex.ui.components.MovingPokeballBackground
import com.austral.pocketdex.ui.components.Profile
import com.austral.pocketdex.ui.components.SettingsModal
import com.austral.pocketdex.ui.components.Sprite
import com.austral.pocketdex.ui.theme.Dimensions
import com.austral.pocketdex.ui.theme.ProfileBackground
import com.austral.pocketdex.viewmodel.HomeViewModel

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()) {
    val context = LocalContext.current

    val found by viewModel.found.collectAsState()
    val total = viewModel.total

    val user by viewModel.userData.collectAsStateWithLifecycle()
    var showSettingsModal by remember { mutableStateOf(false) }

    MovingPokeballBackground()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            modifier = Modifier
                .padding(Dimensions.LargePadding)
                .align(Alignment.TopEnd)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    shape = CircleShape),
            onClick = { showSettingsModal = true }
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(R.string.home_screen_settings_button)
            )
        }
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .clipToBounds(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Dimensions.LargeRoundedCorner))
                    .background(ProfileBackground)
                    .padding(Dimensions.LargePadding * 3)
                    .padding(bottom = Dimensions.LargePadding * 3)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(Dimensions.homeProfileAspectRatio)
                            .aspectRatio(1f)
                    ) {
                        Profile(
                            image = {
                                Sprite(
                                    pokemon = Pokemon.EMPTY,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.size(Dimensions.MediumSpacer))
                    
                    if (user == null) {
                        GoogleLoginButton(
                            modifier = Modifier,
                            onClick = { viewModel.launchCredentialManager(context) }
                        )
                    }

                    Text(user?.displayName ?: "")

                    Spacer(modifier = Modifier.size(Dimensions.SmallSpacer))

                    Text(
                        text = stringResource(R.string.home_screen_found_pokemon, found, total),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.size(Dimensions.LargeSpacer * 4))
        }
    }

    if (showSettingsModal) {
        SettingsModal(
            user = user,
            onDismiss = { showSettingsModal = false },
            onDeleteAllProgress = { viewModel.deleteAll(context) },
            onSignOut = { viewModel.signOut() },
            onSignIn = { viewModel.launchCredentialManager(context) }
        )
    }
}