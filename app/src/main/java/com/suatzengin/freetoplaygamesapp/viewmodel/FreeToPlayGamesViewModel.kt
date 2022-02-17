package com.suatzengin.freetoplaygamesapp.viewmodel


import androidx.lifecycle.*
import com.suatzengin.freetoplaygamesapp.model.Game
import com.suatzengin.freetoplaygamesapp.data.network.GamesApi
import com.suatzengin.freetoplaygamesapp.data.network.GamesApiStatus
import com.suatzengin.freetoplaygamesapp.data.repository.GamesRepository
import kotlinx.coroutines.launch

class FreeToPlayGamesViewModel(
    private val repository: GamesRepository
) : ViewModel() {

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>>
        get() = _games

    private val _status = MutableLiveData<GamesApiStatus>()
    val status: LiveData<GamesApiStatus>
        get() = _status

    init {
        getGames()
    }

    private fun getGames() {
        viewModelScope.launch {
            _status.value = GamesApiStatus.LOADING
            try {
//                _games.value = GamesApi.retrofitService.getGames()
                _games.value = repository.games()
                _status.value = GamesApiStatus.DONE
            } catch (e: Exception) {
                _games.value = ArrayList()
                _status.value = GamesApiStatus.ERROR
            }
        }
    }
}

class FreeToPlayGamesViewModelFactory(
    private val repository: GamesRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if(modelClass.isAssignableFrom(FreeToPlayGamesViewModel::class.java)){
            return FreeToPlayGamesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}