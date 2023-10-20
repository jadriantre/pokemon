package com.example.pokemon.data.repository

import com.example.pokemon.data.database.dao.PokemonDAO
import com.example.pokemon.data.database.entity.CharacterDetailEntity
import com.example.pokemon.data.model.CharacterDetailData
import com.example.pokemon.data.network.GetCharacterService
import com.example.pokemon.domain.model.CharacterDetailDomain
import com.example.pokemon.domain.model.toDomain
import javax.inject.Inject

class GetCharacterRepository @Inject constructor(
    private val getCharacterService: GetCharacterService,
    private val pokemonDAO: PokemonDAO
) {

    suspend fun getCharacterFromAPI(url: String): CharacterDetailDomain {
        val response: CharacterDetailData = getCharacterService.getCharacter(url)
        return response.toDomain()
    }

    suspend fun getCharacterFromDatabase(name: String): CharacterDetailDomain {
        val response: CharacterDetailEntity = pokemonDAO.getCharacter( name ) ?: CharacterDetailEntity(-1,"","","","")
        return response.toDomain()
    }

    suspend fun saveCharacter( character: CharacterDetailEntity ){
        pokemonDAO.insertCharacter( character )
    }

    suspend fun deleteCharacter(name: String){
        pokemonDAO.deleteCharacter(name)
    }
}