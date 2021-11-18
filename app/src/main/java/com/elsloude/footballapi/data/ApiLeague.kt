package com.elsloude.footballapi.data

import com.elsloude.clientbanktest.data.Constants
import com.elsloude.footballapi.data.pojo.league.ResponseLeague
import com.elsloude.footballapi.data.pojo.player.ResponsePlayer
import com.elsloude.footballapi.data.pojo.squad.ResponseSquad
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiLeague {

    @GET("players")
    suspend fun getPlayers(
        @Query("id") id: Int,
        @Query("season") season: Int,
    ): Response<ResponsePlayer>

    @GET("players/squads")
    suspend fun getSquad(
        @Query("team") id: Int,
    ): Response<ResponseSquad>

    companion object {
        operator fun invoke(): ApiLeague {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(Constants.client)
                .build()
                .create(ApiLeague::class.java)
        }
    }
}