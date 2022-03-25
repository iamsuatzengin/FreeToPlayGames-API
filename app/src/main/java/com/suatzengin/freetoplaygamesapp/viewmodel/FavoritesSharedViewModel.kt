package com.suatzengin.freetoplaygamesapp.viewmodel

import androidx.lifecycle.*
import com.suatzengin.freetoplaygamesapp.data.repository.GamesRepository
import com.suatzengin.freetoplaygamesapp.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesSharedViewModel @Inject constructor(
    private val repository: GamesRepository
) : ViewModel() {

    val games: LiveData<List<Game>>

    init {
        games = repository.getGames().asLiveData()
    }

    fun addGameFavorites(game: Game) {
        viewModelScope.launch {
            repository.addGameFavorites(game)
        }
    }

    fun deleteGameFromFavorites(game: Game) {
        viewModelScope.launch {
            repository.deleteGameFromFavorites(game)
        }
    }

}

