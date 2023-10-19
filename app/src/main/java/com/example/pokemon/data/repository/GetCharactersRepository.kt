package com.example.pokemon.data.repository

import com.example.pokemon.data.database.dao.PokemonDAO
import com.example.pokemon.data.database.entity.CharacterEntity
import com.example.pokemon.data.model.CharacterData
import com.example.pokemon.data.network.GetCharactersService
import com.example.pokemon.domain.model.CharacterDomain
import com.example.pokemon.domain.model.toDomain
import javax.inject.Inject

class GetCharactersRepository @Inject constructor(
    private val getCharactersService: GetCharactersService,
    private val pokemonDAO: PokemonDAO
) {

    suspend fun getCharactersFromAPI(): List<CharacterDomain> {
        val response: List<CharacterData> = getCharactersService.getCharacters()
        return response.map { it.toDomain() }
    }

    suspend fun getCharactersFromDatabase(): List<CharacterDomain>{
        val response: List<CharacterEntity> = pokemonDAO.getCharacters()
        return response.map { it.toDomain() }
    }

    suspend fun saveCharacters( characters: List<CharacterEntity> ){
        pokemonDAO.insertCharacters( characters )
    }

    suspend fun deleteCharacters(){
        pokemonDAO.deleteCharacters()
    }
}