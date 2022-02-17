package com.suatzengin.freetoplaygamesapp.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.freetoplaygamesapp.model.Game
import com.suatzengin.freetoplaygamesapp.network.GamesApi
import kotlinx.coroutines.launch

class FreeToPlayGamesViewModel : ViewModel() {

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>>
        get() = _games
    init {
        getGames()
    }

    private fun getGames(){
        viewModelScope.launch {
            try {
                _games.value = GamesApi.retrofitService.getGames()
            }catch (e: Exception){
                _games.value = ArrayList()
            }
        }
    }
}