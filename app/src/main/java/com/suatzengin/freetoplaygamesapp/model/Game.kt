package com.suatzengin.freetoplaygamesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val thumbnail: String,
    @Json(name = "short_description") val shortDescription: String,
    @Json(name = "game_url") val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "freetogame_profile_url") val freeToGameProfileUrl: String
) : Parcelable
