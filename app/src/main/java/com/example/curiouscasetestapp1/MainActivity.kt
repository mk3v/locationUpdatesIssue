package com.example.curiouscasetestapp1

import android.content.*
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context: Context = this@MainActivity
        ApplicationContextProvider.setContext(context)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onStartPendingIntentBasedTestButtonPressed(view: View) {
        LocationHelper.startLocationUpdatesUsingBroadcastReceiverAndPendingIntent()
        startMyService()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onStartCallbackBasedTestButtonPressed(view: View) {
        LocationHelper.startLocationUpdatesUsingCallback()
        startMyService()
    }

    private fun startMyService() {
        val serviceIntent =
            Intent(ApplicationContextProvider.getContext(), MyForegroundService::class.java)
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
        startForegroundService(serviceIntent)
    }
}