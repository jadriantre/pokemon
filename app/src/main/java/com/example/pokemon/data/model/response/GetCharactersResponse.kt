package com.example.pokemon.data.model.response

import com.example.pokemon.data.model.CharacterData

data class GetCharactersResponse ( val count: Int, val results: List<CharacterData> )