package com.dmd.keovecase.viewHolders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmd.keovecase.R
import com.dmd.keovecase.model.Weather
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherViewHolder(inflater: LayoutInflater, parent: ViewGroup, private var context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_weather, parent, false)) {

    fun bind(weather: Weather) {
        val celciusConcat : String = context.resources.getString(R.string.celcius) + weather.temperatureC
        itemView.txtCelcius.text = celciusConcat
        itemView.txtFahrenhit.text = weather.temperatureF.toString()
        itemView.txtSummary.text = weather.summary
        itemView.txtDate.text = weather.date
    }

}