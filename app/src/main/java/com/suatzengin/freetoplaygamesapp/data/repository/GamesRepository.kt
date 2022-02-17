package com.suatzengin.freetoplaygamesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.suatzengin.freetoplaygamesapp.data.local.GamesDao
import com.suatzengin.freetoplaygamesapp.data.network.GamesApi
import com.suatzengin.freetoplaygamesapp.model.Game
import kotlinx.coroutines.flow.Flow

class GamesRepository(private val dao: GamesDao) {

    suspend fun games(): List<Game> = GamesApi.retrofitService.getGames()

    suspend fun addGameFavorites(game: Game) = dao.insert(game)

    suspend fun deleteGameFromFavorites(game: Game) = dao.delete(game)

    fun getGames(): Flow<List<Game>> = dao.getGames()

    fun getGameById(id: Int): Flow<Game> = dao.getGameById(id)
}