package com.example.runtrackerkotlin

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.runtrackerkotlin.R
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.SAXException
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var mlocManager: LocationManager? = null
    var mlocListener: LocationListener? = null
    var isStarted = false
    var startedTime: Date? = null
    var tvStarted: TextView? = null
    var tvElapsed: TextView? = null
    var btStart: Button? = null
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
        mlocListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                tvLat.text = String.format(getString(R.string.latitude_f), location.latitude)
                tvLon.text = String.format(getString(R.string.longitude_f), location.longitude)
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
        }
        mlocManager!!.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 0, 0f,
            mlocListener as LocationListener
        )
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

    private fun decodeGPX(file: File): List<Location> {
        val list: MutableList<Location> = ArrayList()
        val documentBuilderFactory = DocumentBuilderFactory.newInstance()
        try {
            val documentBuilder = documentBuilderFactory.newDocumentBuilder()
            val fileInputStream = FileInputStream(file)
            val document = documentBuilder.parse(fileInputStream)
            val elementRoot = document.documentElement
            val nodelist_trkpt = elementRoot.getElementsByTagName("trkpt")
            for (i in 0 until nodelist_trkpt.length) {
                val node = nodelist_trkpt.item(i)
                val attributes = node.attributes
                val newLatitude = attributes.getNamedItem("lat").textContent
                val newLatitude_double = newLatitude.toDouble()
                val newLongitude = attributes.getNamedItem("lon").textContent
                val newLongitude_double = newLongitude.toDouble()
                val newLocationName = "$newLatitude:$newLongitude"
                val newLocation = Location(newLocationName)
                newLocation.latitude = newLatitude_double
                newLocation.longitude = newLongitude_double
                list.add(newLocation)
            }
            fileInputStream.close()
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return list
    }
}