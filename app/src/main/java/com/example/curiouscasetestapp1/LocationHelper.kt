package com.example.curiouscasetestapp1

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

object LocationHelper {

    const val ACTION_PROCESS_UPDATES = "com.example.curiouscasetestapp1.ACTION_PROCESS_UPDATES"

    const val LOCATION_REQUEST_CODE = 1001

    @SuppressLint("MissingPermission")
    fun startLocationUpdatesUsingBroadcastReceiverAndPendingIntent() {

        val pendingIntent = getBroadcastPendingIntent(
            ApplicationContextProvider.getContext(),
            LocationEventBroadcastReceiver::class.java,
            LOCATION_REQUEST_CODE
        )

        LocationServices.getFusedLocationProviderClient(ApplicationContextProvider.getContext())
            .requestLocationUpdates(getLocationRequest(), pendingIntent)

    }

    private fun getLocationRequest(): LocationRequest {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 100
        return locationRequest
    }

    private fun getBroadcastPendingIntent(
        context: Context,
        receiverClass: Class<*>,
        requestCode: Int
    ): PendingIntent? {
        val intent = Intent(context, receiverClass)
        intent.addFlags(Intent.FLAG_RECEIVER_NO_ABORT)
        intent.action = ACTION_PROCESS_UPDATES
        return PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
    }


    private fun getLocationCallback(): LocationCallback {
        return object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                p0?.let {
                    for (location in p0.locations) {
                        MKLogger.log(
                            "CALLBACK TEST - Location callback: Lat-Long: (${location.latitude}, ${location.longitude}), Acc: ${location.accuracy}"
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdatesUsingCallback() {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(ApplicationContextProvider.getContext())
        fusedLocationProviderClient.requestLocationUpdates(
            getLocationRequest(),
            getLocationCallback(),
            Looper.getMainLooper()
        )
    }
}