package com.elsloude.footballapi.data

class Repository(val api: ApiLeague) {

    suspend fun getPlayers(id: Int, season: Int) = api.getPlayers(id, season)
    suspend fun getSquad(team: Int) = api.getSquad(team)
}