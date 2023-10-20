package com.example.pokemon.data.model.response

data class CharacterDetailResponse( val id: Int, val name: String, val sprites: SpriteResponse?, val height: String, val weight: String, val types: List<CharacterTypesResponse>? )