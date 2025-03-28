package com.austral.pocketdex.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    NavHost(
        navController = navController,
        startDestination = PocketdexScreen.Home.name,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = Dimensions.LargePadding)
            .padding(top = Dimensions.MediumPadding)
    ) {
        composable(route = PocketdexScreen.Home.name) {
            HomeScreen()
        }

        composable(route = PocketdexScreen.Guess.name) {
            GuessScreen()
        }

        composable(route = PocketdexScreen.Dex.name) {
            DexScreen()
        }
    }
}