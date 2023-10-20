package com.example.pokemon.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemon.domain.model.CharacterDetailDomain


@Entity("character_detail")
data class CharacterDetailEntity(@PrimaryKey val id: Int, @ColumnInfo("name") val name: String, @ColumnInfo("sprite") val sprite: String, @ColumnInfo("height") val height: String, @ColumnInfo("weight") val weight: String)

fun CharacterDetailDomain.toEntity() = CharacterDetailEntity( id, name,
    sprite, height, weight )