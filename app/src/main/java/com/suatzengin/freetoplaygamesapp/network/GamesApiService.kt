package com.suatzengin.freetoplaygamesapp.network

import com.suatzengin.freetoplaygamesapp.model.Game
import retrofit2.http.GET

interface GamesApiService {

    @GET("api/games")
    suspend fun getGames(): List<Game>
}