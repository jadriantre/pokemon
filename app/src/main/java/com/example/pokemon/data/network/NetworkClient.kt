package com.example.pokemon.data.network

import com.example.pokemon.data.model.response.GetCharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkClient {
    @GET("pokemon")
    suspend fun getCharacters(): Response<GetCharactersResponse>
}