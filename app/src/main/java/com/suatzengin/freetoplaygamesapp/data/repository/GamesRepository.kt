package com.suatzengin.freetoplaygamesapp.data.repository


import com.suatzengin.freetoplaygamesapp.data.local.GamesDao
import com.suatzengin.freetoplaygamesapp.data.network.GamesApiFilter
import com.suatzengin.freetoplaygamesapp.data.network.GamesApiService
import com.suatzengin.freetoplaygamesapp.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val dao: GamesDao,
    private val gamesApi: GamesApiService
) {

    suspend fun games(filter: GamesApiFilter): List<Game> =
        gamesApi.getGames(filter.value)

    suspend fun addGameFavorites(game: Game) = dao.insert(game)

    suspend fun deleteGameFromFavorites(game: Game) = dao.delete(game)

    fun getGames(): Flow<List<Game>> = dao.getGames()

    fun getGameById(id: Int): Flow<Game> = dao.getGameById(id)
}