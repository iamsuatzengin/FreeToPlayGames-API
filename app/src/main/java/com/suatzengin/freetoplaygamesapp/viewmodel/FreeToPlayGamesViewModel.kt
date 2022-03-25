package com.suatzengin.freetoplaygamesapp.viewmodel


import androidx.lifecycle.*
import com.suatzengin.freetoplaygamesapp.model.Game
import com.suatzengin.freetoplaygamesapp.data.network.GamesApiFilter
import com.suatzengin.freetoplaygamesapp.data.network.GamesApiStatus
import com.suatzengin.freetoplaygamesapp.data.repository.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FreeToPlayGamesViewModel @Inject constructor(
    private val repository: GamesRepository
) : ViewModel() {

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>>
        get() = _games

    private val _status = MutableLiveData<GamesApiStatus>()
    val status: LiveData<GamesApiStatus>
        get() = _status

    init {
        getGames(GamesApiFilter.SHOW_ALL)
    }

    private fun getGames(filter: GamesApiFilter) {
        viewModelScope.launch {
            _status.value = GamesApiStatus.LOADING
            try {
//                _games.value = GamesApi.retrofitService.getGames()
                _games.value = repository.games(filter)
                _status.value = GamesApiStatus.DONE
            } catch (e: Exception) {
                _games.value = ArrayList()
                _status.value = GamesApiStatus.ERROR
            }
        }
    }
    fun updateFilter(filter: GamesApiFilter){
        getGames(filter)
    }
}
