package com.example.pokemon.domain

import com.example.pokemon.core.util.InternetConnectionHelper
import com.example.pokemon.data.database.entity.toEntity
import com.example.pokemon.data.repository.GetCharacterRepository
import com.example.pokemon.domain.model.CharacterDetailDomain
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: GetCharacterRepository,
    private val network: InternetConnectionHelper
) {

    suspend operator fun invoke(name: String, url: String): CharacterDetailDomain {
        var character: CharacterDetailDomain? = null
        if (network.checkInternetConnection())
            character = repository.getCharacterFromAPI(url)
        if (character == null)
            character = repository.getCharacterFromDatabase(name)
        else {
            repository.deleteCharacter(name)
            character.let { repository.saveCharacter(it.toEntity()) }

        }
        return character
    }
}