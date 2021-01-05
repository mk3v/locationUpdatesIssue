package com.example.curiouscasetestapp1

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.Nullable

class MyForegroundService : Service() {
    val CHANNEL_ID = "MyForegroundServiceChannel"
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val input = intent.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification: Notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("My Foreground Service")
                .setContentText(input)
                .setContentIntent(pendingIntent)
                .build()
            startForeground(2001, notification)
        } else {
            val notification: Notification = Notification.Builder(this)
                .setContentTitle("My Foreground Service")
                .setContentText(input)
                .setContentIntent(pendingIntent)
                .build()
            startForeground(2001, notification)
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }
}
