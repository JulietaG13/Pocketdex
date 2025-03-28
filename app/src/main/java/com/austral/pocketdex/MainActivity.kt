package com.austral.pocketdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.austral.pocketdex.navigation.BottomBar
import com.austral.pocketdex.navigation.NavHostComposable
import com.austral.pocketdex.ui.components.PokeCardDialog
import com.austral.pocketdex.ui.components.PokeListItem
import com.austral.pocketdex.ui.theme.PocketdexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PocketdexTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(navController::navigate)
                    }
                ) { innerPadding ->
                    NavHostComposable(innerPadding, navController)
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var showDialog by remember { mutableStateOf(false) }

    PocketdexTheme {
        Row(modifier = Modifier.fillMaxWidth()) {
            PokeListItem(1, true, {})
            PokeListItem(2, true, {})
            PokeListItem(3, true, {})
        }

        Button(onClick = {showDialog = true}) {
            Text("Click me")
        }

        if (showDialog) {
            PokeCardDialog(
                id = 700,
                name = "Sylveon",
                onDismiss = { showDialog = false }
            )
        }
    }
}