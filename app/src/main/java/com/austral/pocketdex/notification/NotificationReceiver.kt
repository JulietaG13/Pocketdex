package com.austral.pocketdex.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.compose.ui.res.stringResource
import androidx.core.app.NotificationCompat
import com.austral.pocketdex.MainActivity
import com.austral.pocketdex.R
import kotlin.random.Random

const val notificationChannelID = "pocketdex_notification_channel"

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val openAppIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, openAppIntent, PendingIntent.FLAG_IMMUTABLE)

        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle(context.getString(R.string.notification_content_title))
            .setContentText(context.getString(R.string.notification_content_text))
            .setSmallIcon(R.drawable.pocket)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(Random.nextInt(), notification)
    }
}