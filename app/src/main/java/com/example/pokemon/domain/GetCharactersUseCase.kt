package com.example.pokemon.domain

import com.example.pokemon.core.util.InternetConnectionHelper
import com.example.pokemon.data.database.entity.toEntity
import com.example.pokemon.data.repository.GetCharactersRepository
import com.example.pokemon.domain.model.CharacterDomain
import javax.inject.Inject

class GetCharactersUseCase@Inject constructor(
    private val repository: GetCharactersRepository,
    private val network: InternetConnectionHelper) {

    suspend operator fun invoke(): List<CharacterDomain> {
        var characters = emptyList<CharacterDomain>()
        if (network.checkInternetConnection())
            characters = repository.getCharactersFromAPI()
        if (characters.isEmpty())
            characters = repository.getCharactersFromDatabase()
        else {
            repository.deleteCharacters()
            repository.saveCharacters( characters.map { it.toEntity() } )
        }
        return characters
    }
}