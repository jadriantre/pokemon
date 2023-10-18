package com.example.pokemon.core

import com.example.pokemon.data.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetmorkModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val baseUrl = "https://pokeapi.co/api/v2/"
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client( okHttpClient.build() )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getClientAPI(retrofit: Retrofit): NetworkClient{
        return retrofit.create(NetworkClient::class.java)
    }
}