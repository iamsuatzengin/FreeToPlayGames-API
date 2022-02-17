package com.suatzengin.freetoplaygamesapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suatzengin.freetoplaygamesapp.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GamesDatabase: RoomDatabase() {

    abstract fun gamesDao(): GamesDao

    companion object{

        @Volatile
        private var INSTANCE: GamesDatabase? = null

        fun getDatabase(context: Context): GamesDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context, GamesDatabase::class.java, "favorite_games_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}