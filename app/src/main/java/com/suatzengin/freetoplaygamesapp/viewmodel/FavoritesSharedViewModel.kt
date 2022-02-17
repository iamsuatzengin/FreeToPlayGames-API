package com.suatzengin.freetoplaygamesapp.viewmodel

import androidx.lifecycle.*
import com.suatzengin.freetoplaygamesapp.data.repository.GamesRepository
import com.suatzengin.freetoplaygamesapp.model.Game
import kotlinx.coroutines.launch

class FavoritesSharedViewModel(
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


class FavoritesSharedViewModelFactory(private val repository: GamesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(FavoritesSharedViewModel::class.java)) {
            return FavoritesSharedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

