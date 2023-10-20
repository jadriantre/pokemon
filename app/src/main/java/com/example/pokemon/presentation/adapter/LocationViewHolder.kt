package com.example.pokemon.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.LocationAdapterBinding
import com.example.pokemon.domain.model.LocationDomain

class LocationViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = LocationAdapterBinding.bind(view)

    fun render(location: LocationDomain) {
        binding.tvLocation.text = "${location.latitude}, ${location.longitude}"
        binding.tvDate.text = location.date
    }
}