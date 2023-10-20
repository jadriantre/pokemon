package com.example.pokemon.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.GetCharacterUseCase
import com.example.pokemon.domain.GetCharactersUseCase
import com.example.pokemon.domain.model.CharacterDetailDomain
import com.example.pokemon.domain.model.CharacterDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
): ViewModel() {

    private val charactersList = MutableLiveData<List<CharacterDomain>>()
    val charactersListObservable = charactersList
    val characters = MutableLiveData<List<CharacterDomain>>()
    val isLoading = MutableLiveData<Boolean>()
    val tryAgain = MutableLiveData<Boolean>()
    val character = MutableLiveData<CharacterDetailDomain?>()
    val isLoadingDetail = MutableLiveData<Boolean>()
    val tryAgainDetail = MutableLiveData<Boolean>()

    fun getCharacters(){
        viewModelScope.launch{
            isLoading.postValue(true)
            val aux = getCharactersUseCase()
            if (aux.isNotEmpty()) {
                tryAgain.postValue(false)
                characters.postValue(aux)
            }else
                tryAgain.postValue(true)
            isLoading.postValue(false)
        }
    }

    fun setCharacters(characters: List<CharacterDomain>){
        charactersList.value = characters
    }

    fun getCharacter( name: String, url: String){
        viewModelScope.launch{
            isLoadingDetail.postValue(true)
            val aux = getCharacterUseCase(name, url)
            if (aux.id != -1) {
                tryAgainDetail.postValue(false)
                character.postValue(aux)
            }else
                tryAgainDetail.postValue(true)
            isLoadingDetail.postValue(false)
        }
    }

}