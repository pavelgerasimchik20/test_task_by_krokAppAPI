package com.geras.krok.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geras.krok.R
import com.geras.krok.data.model.CityDto
import com.geras.krok.domain.model.City

class CityAdapter(private val onItemClickAction: (cityDto: City) -> Unit) :
    RecyclerView.Adapter<CityViewHolder>() {

    var cityList = mutableListOf<City>()

    fun updateCities(cities: List<City>) {
        cityList.clear()
        cityList.addAll(cities)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.city_item, parent, false)
        val holder = CityViewHolder(item)
        item.setOnClickListener { onItemClickAction.invoke(cityList[holder.adapterPosition]) }
        return holder
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {

        val item = cityList[holder.adapterPosition]
        holder.bind(item)
    }

    override fun getItemCount() = cityList.size
}

class CityViewHolder(item: View) :
    RecyclerView.ViewHolder(item) {

    private val ivIcon: ImageView = item.findViewById(R.id.imageView)
    private val cityName: TextView = item.findViewById(R.id.tv_city_item)

    fun bind(city: City) {
        ivIcon.load(city.logo) {
            crossfade(true)
            placeholder(R.drawable.place_holder)
        }
        cityName.text = city.name
    }
}
