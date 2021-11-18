package com.elsloude.footballapi.data.pojo.player

import com.google.gson.annotations.SerializedName

data class PlayerInfo(
    @SerializedName("player")
    val player: Player,
    @SerializedName("statistics")
    val statistics: List<Statistic>
)
