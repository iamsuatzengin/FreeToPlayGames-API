package com.suatzengin.freetoplaygamesapp.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.suatzengin.freetoplaygamesapp.data.local.GamesDao
import com.suatzengin.freetoplaygamesapp.data.local.GamesDatabase
import com.suatzengin.freetoplaygamesapp.data.network.GamesApiService
import com.suatzengin.freetoplaygamesapp.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetroifApi(moshi: Moshi): GamesApiService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Constant.BASE_URL)
            .build().create(GamesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): GamesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            GamesDatabase::class.java,
            "favorite_games_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(database: GamesDatabase): GamesDao {
        return database.gamesDao()
    }

}