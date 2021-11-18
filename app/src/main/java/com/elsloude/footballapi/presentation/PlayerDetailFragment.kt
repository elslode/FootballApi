package com.elsloude.footballapi.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.elsloude.clientbanktest.data.Status
import com.elsloude.footballapi.R
import com.elsloude.footballapi.data.ApiLeague
import com.elsloude.footballapi.data.ModelFactory
import com.elsloude.footballapi.data.Repository
import com.elsloude.footballapi.data.pojo.player.ResponsePlayer
import com.elsloude.footballapi.databinding.FragmentMainBinding
import com.elsloude.footballapi.databinding.FragmentPlayerDetailBinding
import com.squareup.picasso.Picasso
import java.lang.Exception


class PlayerDetailFragment : Fragment() {

    private lateinit var viewModel: ViewModelResponse

    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding: FragmentPlayerDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentPlayerDetailBinding == null")

    private var seasonGame: Int = 2020
    private var id_int: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id_int = it.getInt(KEY_PLAYER)
        }

        setUpViewModel()
        Log.d("playerdalailfragment", "onCreate: $id_int & $seasonGame")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlayer(id_int, seasonGame)
        result()
    }

    private fun setUpViewModel() {
        val api = ApiLeague()
        val repository = Repository(api)
        val factory = ModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ViewModelResponse::class.java)
    }

    fun result(){
        viewModel.playerLD.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.LOADING -> {

                }

                Status.ERROR -> {

                }

                Status.SUCCESS -> {
                    try {
                        if (it.data?.response?.last()?.player != null || it.data?.response?.isNotEmpty()!!) {
                            val playerAge = it.data.response.last().player
                            Log.d("response_player", "result: $playerAge")
                            if (it.data.response.last().player.photo != "") {
                                Picasso.get().load(it.data.response.last().player.photo).into(binding.playerImage)
                            }
                            Picasso.get().load(it.data.response.last().statistics.last().league.logo).into(binding.ivLeagueLogo)
                            Picasso.get().load(it.data.response.last().statistics.last().team.logo).into(binding.ivTeamLogo)
                            binding.tvNameDesc.text = it.data.response.last().player.name
                            binding.tvLastName.text = it.data.response.last().player.lastname
                            binding.tvScoreGames.text = it.data.response.last().statistics.last().games.appearences.toString()
                            binding.tvScoreMinutes.text = it.data.response.last().statistics.last().games.minutes.toString()
                            binding.tvScoreGoals.text = it.data.response.last().statistics.last().goals.total.toString()
                            binding.tvHeight.text = it.data.response.last().player.height
                            binding.tvWeight.text = it.data.response.last().player.weight
                            binding.tvRating.text = it.data.response.last().statistics.last().games.rating.take(3)
                        } else {
                            Toast.makeText(requireContext(), "Sorry, list is empty", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "list is empty", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            PlayerDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PLAYER, id)
                }
            }

        val KEY_PLAYER = "season_getter"
    }
}