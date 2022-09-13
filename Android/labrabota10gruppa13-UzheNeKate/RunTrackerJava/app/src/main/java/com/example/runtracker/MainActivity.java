package com.example.runtracker;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    LocationManager mlocManager;
    boolean isStarted = false;
    Date startedTime;
    TextView tvStarted;
    TextView tvElapsed;
    Button btStart;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvOut = (TextView) findViewById(R.id.out);
        TextView tvLon = (TextView) findViewById(R.id.longitude);
        TextView tvLat = (TextView) findViewById(R.id.latitude);
        tvStarted = (TextView) findViewById(R.id.started);
        tvElapsed = (TextView) findViewById(R.id.elapsed);
        tvStarted.setText("");
        tvElapsed.setText("");
        mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = new LocationRequest();
        locationRequest.setInterval(20000);
        locationRequest.setFastestInterval(30000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        checkLocationService(this, fusedLocationClient, locationRequest, locationCallback,
                locationSettingsResponse -> Log.d("SUCCESS", "Connection to location service succeeded"),
                e -> Log.d("FAIL", "Connection to location service failed"));
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Log.d("Location result", "IS NULL");
                    return;
                }
                List<Location> locations = locationResult.getLocations();
                Log.d("SIZE", String.valueOf(locations.size()));
                for (Location location : locations) {
                    tvLon.setText(String.format(getString(R.string.longitude_f), location.getLongitude()));
                    tvLat.setText(String.format(getString(R.string.latitude_f), location.getLatitude()));
                }
            }
        };

        if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tvOut.setText("GPS is turned on...");
        } else {
            tvOut.setText("GPS is not turned on...");
        }

        btStart = (Button) findViewById(R.id.button);
        btStart.setOnClickListener(l ->
        {
            isStarted = !isStarted;
            if (isStarted) {
                start();
            } else {
                stop();
            }
        });
    }

    private void start() {
        startedTime = new Date();
        tvStarted.setText(String.format(getString(R.string.started_s), startedTime.toLocaleString()));
        tvElapsed.setText("");
        btStart.setText(R.string.stop);
    }

    private void stop() {
        long dif = new Date().getTime() - startedTime.getTime();
        tvElapsed.setText(String.format(getString(R.string.elapsed_f_s), dif / 1000.));
        btStart.setText(R.string.start);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates(this, fusedLocationClient, locationRequest, locationCallback);
    }


    public static void checkLocationService(Activity activity, FusedLocationProviderClient client, LocationRequest request,
                                            LocationCallback callback,
                                            final OnSuccessListener<LocationSettingsResponse> successListener, OnFailureListener failureListener) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(request);

        SettingsClient settingsClient = LocationServices.getSettingsClient(activity);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(activity, (OnSuccessListener<LocationSettingsResponse>) locationSettingsResponse -> {
            startLocationUpdates(activity, client, request, callback);
            successListener.onSuccess(locationSettingsResponse);
        });

        task.addOnFailureListener(activity, failureListener);
    }


    private static void startLocationUpdates(Activity activity, FusedLocationProviderClient client,
                                             LocationRequest request, LocationCallback callback) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
        client.requestLocationUpdates(
                request,
                callback,
                Looper.getMainLooper());
    }

}