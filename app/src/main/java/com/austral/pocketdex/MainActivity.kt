package com.austral.pocketdex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.austral.pocketdex.navigation.BottomBar
import com.austral.pocketdex.navigation.NavHostComposable
import com.austral.pocketdex.notification.ScheduleNotificationManager
import com.austral.pocketdex.notification.notificationChannelID
import com.austral.pocketdex.ui.theme.PocketdexTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    var notificationManager: ScheduleNotificationManager = ScheduleNotificationManager(this)

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        createNotificationChannel()

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

    override fun onStop() {
        super.onStop()
        notificationManager.scheduleNotification()
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun createNotificationChannel() {
        val notificationChannel = NotificationChannel(
            notificationChannelID,
            this.getString(R.string.notification_channel),
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(notificationChannel)
    }
}