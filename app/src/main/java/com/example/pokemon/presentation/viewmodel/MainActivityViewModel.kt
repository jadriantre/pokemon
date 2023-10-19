package com.example.pokemon.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.GetCharactersUseCase
import com.example.pokemon.domain.model.CharacterDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
): ViewModel() {

    val characters = MutableLiveData<List<CharacterDomain>>()
    val isLoading = MutableLiveData<Boolean>()
    val tryAgain = MutableLiveData<Boolean>()

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

    fun setCharacters(list: List<CharacterDomain>){
        characters.postValue(list)
    }

}