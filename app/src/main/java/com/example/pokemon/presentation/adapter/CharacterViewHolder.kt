package com.example.pokemon.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.CharacterAdapterBinding
import com.example.pokemon.domain.model.CharacterDomain
import io.reactivex.subjects.PublishSubject

class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = CharacterAdapterBinding.bind(view)

    fun render(character: CharacterDomain, click: PublishSubject<CharacterDomain>) {
        binding.tvName.text = character.name
        binding.circularImageView.setImageOrName("", character.name)
        binding.cv.setOnClickListener { click.onNext(character) }
    }
}