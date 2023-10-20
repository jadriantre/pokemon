package com.example.pokemon.data.network

import com.example.pokemon.data.model.CharacterDetailData
import com.example.pokemon.data.model.toData
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterService @Inject constructor(private val networkClient: NetworkClient) {

    suspend fun getCharacter( url: String ): CharacterDetailData {
        lateinit var result: CharacterDetailData
        withContext(Dispatchers.IO) {
            result = try{
                val response = networkClient.getCharacter( url )
                if( response.isSuccessful )
                    response.body()!!.toData()
                else
                    CharacterDetailData(0,"","", "", "")
            }catch( error: HttpException ){
                CharacterDetailData(0,"","", "", "")
            }catch( error: HttpException ){
                CharacterDetailData(0,"","", "", "")
            }
            catch( error: JsonSyntaxException){
                CharacterDetailData(0,"","", "", "")
            }
            catch( error: IOException){
                CharacterDetailData(0,"","", "", "")
            }
            catch( error: Exception ){
                CharacterDetailData(0,"","", "", "")
            }
        }
        return result
    }
}