package com.elsloude.footballapi.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elsloude.clientbanktest.data.Resource
import com.elsloude.footballapi.data.Repository
import com.elsloude.footballapi.data.pojo.league.ResponseLeague
import com.elsloude.footballapi.data.pojo.player.ResponsePlayer
import com.elsloude.footballapi.data.pojo.squad.ResponseSquad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ViewModelResponse(private val repository: Repository) : ViewModel() {

    val playerLD = MutableLiveData<Resource<ResponsePlayer>>()
    val squad = MutableLiveData<Resource<ResponseSquad>>()

    fun getPlayer(id: Int, season: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            playerLD.postValue(Resource.loading(null))
            try {
                val datSign = async { repository.getPlayers(id, season) }
                val user = datSign.await().body()
                playerLD.postValue(Resource.success(user))
                Log.d("getPlayer", "getLeague: $user")
            } catch (e: Exception) {
                playerLD.postValue(Resource.error("Sorry, data is not available", null))
                Log.d("getPlayer", "getLeague: ${e.message}")
            }
        }
    }

    fun getSquad(team: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            squad.postValue(Resource.loading(null))
            try {
                val datSign = async { repository.getSquad(team) }
                val user = datSign.await().body()
                squad.postValue(Resource.success(user))
                Log.d("getSquad", "getLeague: $user")
            } catch (e: Exception) {
                squad.postValue(Resource.error("Sorry, data is not available", null))
                Log.d("getSquad", "getLeague: ${e.message}")
            }
        }
    }
}