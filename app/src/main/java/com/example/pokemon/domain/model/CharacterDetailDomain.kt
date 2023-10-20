package com.example.pokemon.domain.model

import com.example.pokemon.data.database.entity.CharacterDetailEntity
import com.example.pokemon.data.model.CharacterDetailData

data class CharacterDetailDomain(val id: Int, val name: String, val sprite: String, val height: String, val weight: String)

fun CharacterDetailData.toDomain() = CharacterDetailDomain( id, name, sprite, height, weight )

fun CharacterDetailEntity.toDomain() = CharacterDetailDomain( id, name, sprite , height, weight )
