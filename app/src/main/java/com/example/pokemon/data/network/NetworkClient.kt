package com.example.pokemon.data.network

import com.example.pokemon.data.model.response.CharacterDetailResponse
import com.example.pokemon.data.model.response.GetCharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkClient {
    @GET("pokemon")
    suspend fun getCharacters(): Response<GetCharactersResponse>

    @GET
    suspend fun getCharacter( @Url url: String ): Response<CharacterDetailResponse>
}