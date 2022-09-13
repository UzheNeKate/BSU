package com.example.runtracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
    LocationListener mlocListener;
    boolean isStarted = false;
    Date startedTime;
    TextView tvStarted;
    TextView tvElapsed;
    Button btStart;

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
        mlocManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                tvLat.setText(String.format(getString(R.string.latitude_f), location.getLatitude()));
                tvLon.setText(String.format(getString(R.string.longitude_f), location.getLongitude()));
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
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

    private List<Location> decodeGPX(File file) {
        List<Location> list = new ArrayList<Location>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            FileInputStream fileInputStream = new FileInputStream(file);
            Document document = documentBuilder.parse(fileInputStream);
            Element elementRoot = document.getDocumentElement();

            NodeList nodelist_trkpt = elementRoot.getElementsByTagName("trkpt");

            for (int i = 0; i < nodelist_trkpt.getLength(); i++) {
                Node node = nodelist_trkpt.item(i);
                NamedNodeMap attributes = node.getAttributes();

                String newLatitude = attributes.getNamedItem("lat").getTextContent();
                double newLatitude_double = Double.parseDouble(newLatitude);

                String newLongitude = attributes.getNamedItem("lon").getTextContent();
                double newLongitude_double = Double.parseDouble(newLongitude);

                String newLocationName = newLatitude + ":" + newLongitude;
                Location newLocation = new Location(newLocationName);
                newLocation.setLatitude(newLatitude_double);
                newLocation.setLongitude(newLongitude_double);

                list.add(newLocation);

            }
            fileInputStream.close();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return list;
    }


}