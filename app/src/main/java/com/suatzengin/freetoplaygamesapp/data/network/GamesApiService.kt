package com.suatzengin.freetoplaygamesapp.data.network

import com.suatzengin.freetoplaygamesapp.model.Game
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApiService {

    @GET("api/games")
    suspend fun getGames(@Query("platform") platform: String): List<Game>
}