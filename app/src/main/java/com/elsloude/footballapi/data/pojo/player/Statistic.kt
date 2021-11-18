package com.elsloude.footballapi.data.pojo.player

import com.google.gson.annotations.SerializedName

data class Statistic(
    @SerializedName("team")
    val team: Team,
    @SerializedName("league")
    val league: League,
    @SerializedName("games")
    val games: Games,
//    @SerializedName("shots")
//    val shots: Shots,
    @SerializedName("goals")
    val goals: Goals,
//    @SerializedName("passes")
//    val passes: Passes,
//    @SerializedName("tackles")
//    val tackles: Tackles,
//    @SerializedName("duels")
//    val duels: Duels,
//    @SerializedName("dribbles")
//    val dribbles: Dribbles,
//    @SerializedName("fouls")
//    val fouls: Fouls,
//    @SerializedName("cards")
//    val cards: Cards,
//    @SerializedName("penalty")
//    val penalty: Penalty,
)