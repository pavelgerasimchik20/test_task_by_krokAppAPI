package com.geras.krok.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geras.krok.R
import com.geras.krok.domain.model.PointOfInterest

class PointAdapter(private val onItemClickAction: (point: PointOfInterest) -> Unit) :
    RecyclerView.Adapter<PointViewHolder>() {

    var listPoints = mutableListOf<PointOfInterest>()

    fun updatePoints(points: List<PointOfInterest>) {
        listPoints.clear()
        listPoints.addAll(points)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.point_item, parent, false)
        val holder = PointViewHolder(item)
        item.setOnClickListener { onItemClickAction.invoke(listPoints[holder.adapterPosition]) }
        return holder
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {

        val item = listPoints[holder.adapterPosition]
        holder.bind(item)
    }

    override fun getItemCount() = listPoints.size
}

class PointViewHolder(item: View) :
    RecyclerView.ViewHolder(item) {

    private val ivPoint: ImageView = item.findViewById(R.id.iv_point_item)
    private val tvPoint: TextView = item.findViewById(R.id.tv_point_item)

    fun bind(point: PointOfInterest) {
        ivPoint.load(point.logo) {
            crossfade(true)
            placeholder(R.drawable.place_holder)
        }
        tvPoint.text = point.name
    }
}