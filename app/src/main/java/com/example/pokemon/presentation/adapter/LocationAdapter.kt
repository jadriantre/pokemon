package com.example.pokemon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.domain.model.LocationDomain

class LocationAdapter(private val data: List<LocationDomain>): RecyclerView.Adapter<LocationViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.location_adapter, viewGroup, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: LocationViewHolder, position: Int) {
        val location = data[position]
        viewHolder.render(location)
    }

    override fun getItemCount() = data.size
}