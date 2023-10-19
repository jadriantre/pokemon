package com.example.pokemon.domain.model

import com.example.pokemon.data.database.entity.CharacterEntity
import com.example.pokemon.data.model.CharacterData

data class CharacterDomain(val name: String, val url: String)

fun CharacterData.toDomain() = CharacterDomain( name, url )

fun CharacterEntity.toDomain() = CharacterDomain( name, url )