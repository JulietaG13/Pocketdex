package com.austral.pocketdex.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.austral.pocketdex.ui.screens.DexScreen
import com.austral.pocketdex.ui.screens.GuessScreen
import com.austral.pocketdex.ui.screens.HomeScreen
import com.austral.pocketdex.ui.theme.Dimensions

@Composable
fun NavHostComposable(
    innerPadding: PaddingValues,
    navController: NavHostController
) {

    val insets = WindowInsets.statusBars.asPaddingValues()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(insets.calculateTopPadding())
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )

        NavHost(
            navController = navController,
            startDestination = PocketdexScreen.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = insets.calculateTopPadding())
        ) {
            composable(route = PocketdexScreen.Home.name) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = Dimensions.LargePadding)
                ) {
                    HomeScreen()
                }
            }

            composable(route = PocketdexScreen.Guess.name) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = Dimensions.LargePadding)
                ) {
                    GuessScreen()
                }
            }

            composable(route = PocketdexScreen.Dex.name) {
                DexScreen()
            }
        }
    }
}