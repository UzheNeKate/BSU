package com.example.weather

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    private var cities: MutableList<String> = ArrayList()
    private var weathers: MutableList<Weather> = ArrayList()
    private var apiKey = "d43f2805e8eb108bd6666702f5609c05"
    private var rvMain: RecyclerView? = null
    private var layoutManager: LayoutManager? = null
    private var adapter: RecyclerAdapter? = null
    private var fabUpdate: FloatingActionButton? = null
    private var btAdd: Button? = null
    private var etCity: EditText? = null
    private var queue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queue = Volley.newRequestQueue(this)
        cities.add("Minsk")
        cities.add("Hrodna")
        setUpViews()
        allWeather
    }

    private fun updateClick() {
        weathers.clear()
        allWeather
        Snackbar.make(this, rvMain!!, "Updating...", Snackbar.LENGTH_LONG).show()
    }

    private fun addClick() {
        Snackbar.make(this, rvMain!!, "Adding new city...", Snackbar.LENGTH_LONG).show()
        val city = etCity!!.text.toString()
        getCityWeather(city)
        cities.add(city)
    }

    private val allWeather: Unit
        get() {
            for (city in cities) {
                getCityWeather(city)
            }
        }

    private fun getCityWeather(city: String) {
        val url = String.format(
            "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
            city, apiKey
        )

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                weathers.add(Gson().fromJson(response, Weather::class.java))
                adapter!!.notifyItemInserted(weathers.size - 1)
            }
        ) { error -> Log.d("ERROR", error.toString()) }
        queue!!.add(stringRequest)
    }

    private fun setUpViews() {
        rvMain = findViewById(R.id.rvMain)
        layoutManager = LinearLayoutManager(this)
        rvMain!!.layoutManager = layoutManager
        adapter = RecyclerAdapter(weathers, this)
        rvMain!!.adapter = adapter
        fabUpdate = findViewById(R.id.fabRefresh)
        fabUpdate!!.setOnClickListener { updateClick() }
        btAdd = findViewById(R.id.btAdd)
        btAdd!!.setOnClickListener { addClick() }
        etCity = findViewById(R.id.etCity)
    }
}