package com.suatzengin.freetoplaygamesapp.data.repository


import com.suatzengin.freetoplaygamesapp.data.local.GamesDao
import com.suatzengin.freetoplaygamesapp.data.network.GamesApi
import com.suatzengin.freetoplaygamesapp.data.network.GamesApiFilter
import com.suatzengin.freetoplaygamesapp.model.Game
import kotlinx.coroutines.flow.Flow

class GamesRepository(private val dao: GamesDao) {

    suspend fun games(filter: GamesApiFilter): List<Game> =
        GamesApi.retrofitService.getGames(filter.value)

    suspend fun addGameFavorites(game: Game) = dao.insert(game)

    suspend fun deleteGameFromFavorites(game: Game) = dao.delete(game)

    fun getGames(): Flow<List<Game>> = dao.getGames()

    fun getGameById(id: Int): Flow<Game> = dao.getGameById(id)
}