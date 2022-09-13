package com.example.runtrackerkotlin

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import java.util.*

class MainActivity : AppCompatActivity() {
    var mlocManager: LocationManager? = null
    var isStarted = false
    var startedTime: Date? = null
    var tvStarted: TextView? = null
    var tvElapsed: TextView? = null
    var btStart: Button? = null
    private var locationCallback: LocationCallback? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvOut = findViewById<View>(R.id.out) as TextView
        val tvLon = findViewById<View>(R.id.longitude) as TextView
        val tvLat = findViewById<View>(R.id.latitude) as TextView
        tvStarted = findViewById<View>(R.id.started) as TextView
        tvElapsed = findViewById<View>(R.id.elapsed) as TextView
        tvStarted!!.text = ""
        tvElapsed!!.text = ""
        mlocManager = getSystemService(LOCATION_SERVICE) as LocationManager
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest()
        locationRequest!!.interval = 20000
        locationRequest!!.fastestInterval = 30000
        locationRequest!!.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        checkLocationService(this, fusedLocationClient, locationRequest, locationCallback,
            {
                Log.d(
                    "SUCCESS",
                    "Connection to location service succeeded"
                )
            },
            { Log.d("FAIL", "Connection to location service failed") })
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val locations: List<Location> = locationResult.locations
                Log.d("SIZE", locations.size.toString())
                for (location in locations) {
                    tvLon.text = String.format(getString(R.string.longitude_f), location.longitude)
                    tvLat.text = String.format(getString(R.string.latitude_f), location.latitude)
                }
            }
        }.also { locationCallback = it }
        if (mlocManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tvOut.text = "GPS is turned on..."
        } else {
            tvOut.text = "GPS is not turned on..."
        }
        btStart = findViewById<View>(R.id.button) as Button
        btStart!!.setOnClickListener { l: View? ->
            isStarted = !isStarted
            if (isStarted) {
                start()
            } else {
                stop()
            }
        }
    }

    private fun start() {
        startedTime = Date()
        tvStarted!!.text =
            String.format(getString(R.string.started_s), startedTime!!.toLocaleString())
        tvElapsed!!.text = ""
        btStart!!.setText(R.string.stop)
    }

    private fun stop() {
        val dif = Date().time - startedTime!!.time
        tvElapsed!!.text = String.format(getString(R.string.elapsed_f_s), dif / 1000.0)
        btStart!!.setText(R.string.start)
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates(this, fusedLocationClient, locationRequest, locationCallback)
    }

    companion object {
        fun checkLocationService(
            activity: Activity,
            client: FusedLocationProviderClient?,
            request: LocationRequest?,
            callback: LocationCallback?,
            successListener: OnSuccessListener<LocationSettingsResponse>,
            failureListener: OnFailureListener?
        ) {
            val builder: LocationSettingsRequest.Builder = LocationSettingsRequest.Builder()
                .addLocationRequest(request!!)
            val settingsClient: SettingsClient = LocationServices.getSettingsClient(activity)
            val task: Task<LocationSettingsResponse> =
                settingsClient.checkLocationSettings(builder.build())
            task.addOnSuccessListener(
                activity
            ) { locationSettingsResponse ->
                startLocationUpdates(activity, client, request, callback)
                successListener.onSuccess(locationSettingsResponse)
            }
            task.addOnFailureListener(activity, failureListener!!)
        }

        private fun startLocationUpdates(
            activity: Activity, client: FusedLocationProviderClient?,
            request: LocationRequest?, callback: LocationCallback?
        ) {
            if (ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    0
                )
            }
            client!!.requestLocationUpdates(
                request!!,
                callback!!,
                Looper.getMainLooper()
            )
        }
    }
}