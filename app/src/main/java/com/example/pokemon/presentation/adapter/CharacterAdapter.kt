package com.example.pokemon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.domain.model.CharacterDomain
import io.reactivex.subjects.PublishSubject
import com.example.pokemon.R

class CharacterAdapter(private val data: List<CharacterDomain>): RecyclerView.Adapter<CharacterViewHolder>() {

    private val click = PublishSubject.create<CharacterDomain>()
    val clickEvent: io.reactivex.Observable<CharacterDomain> = click

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.character_adapter, viewGroup, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CharacterViewHolder, position: Int) {
        val character = data[position]
        viewHolder.render(character, click)
    }

    override fun getItemCount() = data.size
}