package com.example.pokemon.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemon.data.database.dao.PokemonDAO
import com.example.pokemon.data.database.entity.CharacterEntity

@Database( entities = [CharacterEntity::class], version = 1 )
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getDAO(): PokemonDAO
}