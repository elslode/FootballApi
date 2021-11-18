package com.elsloude.footballapi.data.pojo.squad

import com.google.gson.annotations.SerializedName

data class AllCommand(
    @SerializedName("team")
    val team: Team,
    @SerializedName("players")
    val players: List<PlayerSquad>
)
