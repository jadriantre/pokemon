package com.example.pokemon.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemon.data.model.CharacterData
import com.example.pokemon.domain.model.CharacterDomain

@Entity("character")
data class CharacterEntity(@PrimaryKey(autoGenerate = true) val id: Int = 0, @ColumnInfo("name") val name: String, @ColumnInfo("url") val url: String)

fun CharacterDomain.toEntity() = CharacterEntity( id = 0, name = name, url = url )

fun CharacterData.toData() = CharacterData( name = name, url = url )