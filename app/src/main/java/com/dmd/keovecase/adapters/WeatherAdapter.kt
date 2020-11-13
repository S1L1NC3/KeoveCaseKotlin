package com.dmd.keovecase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmd.keovecase.model.Weather
import com.dmd.keovecase.viewHolders.WeatherViewHolder

class WeatherAdapter(private val dataList: ArrayList<Weather>): RecyclerView.Adapter<WeatherViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return WeatherViewHolder(inflater,parent, parent.context)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather: Weather = dataList[position]
        holder.bind(weather)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateList(newDataList: ArrayList<Weather>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }
}