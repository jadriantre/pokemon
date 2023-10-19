package com.example.pokemon.data.model

import com.example.pokemon.data.database.entity.CharacterEntity

data class CharacterData (val name: String, val url: String)

fun CharacterData.toEntity() = CharacterEntity( id = 0, name = name, url = url )