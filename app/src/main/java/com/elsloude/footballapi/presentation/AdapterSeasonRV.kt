package com.elsloude.footballapi.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.elsloude.footballapi.R
import com.elsloude.footballapi.data.pojo.squad.AllCommand
import com.elsloude.footballapi.data.pojo.squad.PlayerSquad
import com.squareup.picasso.Picasso

class AdapterSeasonRV(var listTeam: List<PlayerSquad>, val listener: (PlayerSquad) -> Unit):  RecyclerView.Adapter<AdapterSeasonRV.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPlayer = itemView.findViewById<ImageView>(R.id.iv_player_squad)
        val tvPlayer = itemView.findViewById<TextView>(R.id.iv_name_of_player_squad)
        val tvPosition = itemView.findViewById<TextView>(R.id.tv_position)
        val tvNumberPlayer = itemView.findViewById<TextView>(R.id.tv_number_player)
        fun bind(
            listSeason: PlayerSquad,
            listener: (PlayerSquad) -> Unit
        ) {
            try {
                Picasso.get().load(listSeason.photo).into(ivPlayer)
                tvPlayer.text = listSeason.name
                tvNumberPlayer.text = listSeason.number.toString()
                tvPosition.text = listSeason.position
                itemView.setOnClickListener {
                    listener(listSeason)
                }
            } catch (e: Exception) {
                Log.d("asdfasdf", "bind: ")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_squad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = listTeam[position]
        holder.bind(content, listener)
    }

    override fun getItemCount(): Int {
        return listTeam.size
    }
}