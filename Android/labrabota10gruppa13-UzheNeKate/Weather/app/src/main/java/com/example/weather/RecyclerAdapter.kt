package com.example.weather

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(var weathers: List<Weather>, var ctx: Context) :
    RecyclerView.Adapter<RecyclerAdapter.WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val w = weathers[position]
        holder.tvCityName.text = w.name
        holder.tvTemperature.text = String.format(ctx.getString(R.string.temp_2f_c), w.main!!.temp)
        holder.tvHumidity.text =
            String.format(ctx.getString(R.string.humidity_0f), w.main!!.humidity)
        holder.tvWind.text = String.format(ctx.getString(R.string.wind_1f_m_s), w.wind!!.speed)
    }

    override fun getItemCount(): Int {
        return weathers.size
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCityName: TextView = itemView.findViewById(R.id.tvCityName)
        var tvTemperature: TextView = itemView.findViewById(R.id.tvTemperature)
        var tvHumidity: TextView = itemView.findViewById(R.id.tvHumidity)
        var tvWind: TextView = itemView.findViewById(R.id.tvWind)

    }
}
