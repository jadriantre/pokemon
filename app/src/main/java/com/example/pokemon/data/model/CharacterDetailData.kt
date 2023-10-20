package com.example.pokemon.data.model

import com.example.pokemon.data.model.response.CharacterDetailResponse

data class CharacterDetailData ( val id: Int, val name: String, val sprite: String, val height: String, val weight: String )

fun CharacterDetailResponse.toData() = CharacterDetailData( id, name,
    sprites?.front ?: "", height, weight )
