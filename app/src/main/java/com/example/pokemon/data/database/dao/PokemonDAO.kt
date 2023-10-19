package com.example.pokemon.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemon.data.database.entity.CharacterEntity

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM character ORDER BY id ASC")
    suspend fun getCharacters(): List<CharacterEntity>

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertCharacters( characters: List<CharacterEntity> )

    @Query("DELETE FROM character")
    suspend fun deleteCharacters()
}