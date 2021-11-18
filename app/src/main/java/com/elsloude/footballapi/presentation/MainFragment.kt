package com.elsloude.footballapi.presentation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elsloude.clientbanktest.data.Status
import com.elsloude.footballapi.R
import com.elsloude.footballapi.data.ApiLeague
import com.elsloude.footballapi.data.ModelFactory
import com.elsloude.footballapi.data.Repository
import com.elsloude.footballapi.databinding.FragmentMainBinding
import com.squareup.picasso.Picasso

class MainFragment : Fragment() {

    private lateinit var viewModel: ViewModelResponse

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private var id_team: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.recyclerSquad.layoutManager = LinearLayoutManager(requireContext())
        binding.textViewPrivacy.setOnClickListener {
            val url = "https://www.google.com/"
            val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder();
            val customTabsIntent: CustomTabsIntent = builder.build();
            customTabsIntent.launchUrl(requireContext(), Uri.parse(url));
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result()
        binding.arrowNext.setOnClickListener {
            id_team++
            viewModel.getSquad(id_team)
        }
        binding.arrowPrevious.setOnClickListener {
            if (id_team == 1) {
                id_team
            } else {
                id_team--
            }
            viewModel.getSquad(id_team)
        }
        viewModel.getSquad(id_team)
    }

    fun result(){
        viewModel.squad.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.LOADING -> {
                    binding.shimmerViewContainer.visibility = View.VISIBLE
                    binding.textAvailable.visibility = View.INVISIBLE
                    binding.recyclerSquad.visibility = View.INVISIBLE
                }
                Status.ERROR -> {
                    binding.textAvailable.visibility = View.VISIBLE
                    binding.shimmerViewContainer.visibility = View.INVISIBLE
                    binding.recyclerSquad.visibility = View.INVISIBLE
                }
                Status.SUCCESS -> {
                    try {
                        binding.recyclerSquad.visibility = View.VISIBLE
                        binding.textAvailable.visibility = View.INVISIBLE
                        binding.shimmerViewContainer.visibility = View.INVISIBLE
                        val squad = it.data?.response!![0].players
                        Picasso.get().load(it.data.response[0].team.logo).into(binding.ivTeam)
                        binding.tvNameOfTeam.text = it.data.response[0].team.name
                        binding.recyclerSquad.adapter = AdapterSeasonRV(squad) {
                            parentFragmentManager.beginTransaction().replace(R.id.container, PlayerDetailFragment.newInstance(it.id)).addToBackStack(null).commit()
                        }
                    } catch (e: Exception) {
                        binding.textAvailable.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setUpViewModel() {
        val api = ApiLeague()
        val repository = Repository(api)
        val factory = ModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ViewModelResponse::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}