package com.example.pokemon.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemon.data.database.entity.CharacterDetailEntity
import com.example.pokemon.data.database.entity.CharacterEntity

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM character ORDER BY id ASC")
    suspend fun getCharacters(): List<CharacterEntity>

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertCharacters( characters: List<CharacterEntity> )

    @Query("DELETE FROM character")
    suspend fun deleteCharacters()

    @Query("SELECT * FROM character_detail WHERE name = :name")
    suspend fun getCharacter( name: String): CharacterDetailEntity?

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertCharacter( character: CharacterDetailEntity )

    @Query("DELETE FROM character_detail WHERE name = :name")
    suspend fun deleteCharacter(name: String)
}