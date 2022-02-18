package com.suatzengin.freetoplaygamesapp.data.local

import androidx.room.*
import com.suatzengin.freetoplaygamesapp.model.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: Game)

    @Delete
    suspend fun delete(game: Game)

    @Query("SELECT * FROM game")
    fun getGames(): Flow<List<Game>>

    @Query("SELECT * FROM game WHERE id = :id")
    fun getGameById(id: Int): Flow<Game>

}