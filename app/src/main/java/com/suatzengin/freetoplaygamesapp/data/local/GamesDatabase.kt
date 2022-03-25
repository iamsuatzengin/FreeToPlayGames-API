package com.suatzengin.freetoplaygamesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suatzengin.freetoplaygamesapp.model.Game

@Database(entities = [Game::class], version = 3, exportSchema = false)
abstract class GamesDatabase: RoomDatabase() {

    abstract fun gamesDao(): GamesDao

}