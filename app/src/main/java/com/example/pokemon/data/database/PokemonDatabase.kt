package com.example.pokemon.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemon.data.database.dao.PokemonDAO
import com.example.pokemon.data.database.entity.CharacterDetailEntity
import com.example.pokemon.data.database.entity.CharacterEntity

@Database( entities = [CharacterEntity::class, CharacterDetailEntity::class], version = 2, exportSchema = false )
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getDAO(): PokemonDAO
}