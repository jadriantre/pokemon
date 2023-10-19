package com.example.pokemon.data.network

import com.example.pokemon.data.model.CharacterData
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersService @Inject constructor(private val networkClient: NetworkClient) {

    suspend fun getCharacters(): List<CharacterData> {
        lateinit var result: List<CharacterData>
        withContext(Dispatchers.IO) {
            result = try{
                val response = networkClient.getCharacters()
                if( response.isSuccessful )
                    response.body()?.results ?: listOf(CharacterData("",""))
                else
                    listOf(CharacterData("",""))
            }catch( error: HttpException ){
                listOf(CharacterData(error.message(),""))
            }catch( error: HttpException ){
                listOf(CharacterData(error.message(),""))
            }
            catch( error: JsonSyntaxException){
                listOf(CharacterData(error.message.toString(),""))
            }
            catch( error: IOException){
                listOf(CharacterData(error.message.toString(),""))
            }
            catch( error: Exception ){
                listOf(CharacterData(error.message.toString(),""))
            }
        }
        return result
    }
}