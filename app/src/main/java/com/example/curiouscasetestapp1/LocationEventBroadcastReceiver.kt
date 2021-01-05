package com.example.curiouscasetestapp1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.curiouscasetestapp1.MKLogger.log
import com.google.android.gms.location.*

class LocationEventBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent != null) {
            val action = intent.action
            if (LocationHelper.ACTION_PROCESS_UPDATES.equals(action)) {
                try {
                    processIntent(context, intent)
                } catch (e: Exception) {
                    log("processIntent error\n$e")
                }
            }
        }
    }

    private fun processIntent(context: Context, intent: Intent) {
        if (LocationResult.hasResult(intent)) {
            try {
                val locationResult = LocationResult.extractResult(intent)
                if (locationResult != null) {
                    log(locationResult.toString())
                }
            } catch (t: Throwable) {
                log("LocationResult could not be extracted\n$t") // NON-NLS
            }
        } else if (LocationAvailability.hasLocationAvailability(intent)) {
            val locationAvailability =
                LocationAvailability.extractLocationAvailability(intent)
            if (locationAvailability == null) {
                log("LocationEventBroadcastReceiver: unable to extract LocationAvailability")
            } else {
                log(
                    "LocationEventBroadcastReceiver onHandleIntent LocationAvailability: isLocationAvailable: "
                            + locationAvailability.isLocationAvailable
                )
            }
        }
    }

}