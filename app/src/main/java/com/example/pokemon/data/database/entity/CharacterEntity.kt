package com.example.pokemon.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("character")
data class CharacterEntity(@PrimaryKey val id: Int)