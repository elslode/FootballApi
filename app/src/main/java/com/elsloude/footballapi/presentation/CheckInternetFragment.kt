package com.elsloude.footballapi.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elsloude.footballapi.R
import com.elsloude.footballapi.databinding.FragmentCheckInternetBinding
import com.elsloude.footballapi.databinding.FragmentMainBinding


class CheckInternetFragment : Fragment() {

    private var _binding: FragmentCheckInternetBinding? = null
    private val binding: FragmentCheckInternetBinding
        get() = _binding ?: throw RuntimeException("FragmentCheckInternetBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckInternetBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonInternet.setOnClickListener {
            if (connection(requireContext())) {
                parentFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
            }
        }
    }

    fun connection(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.activeNetworkInfo
        return wifiInfo != null && wifiInfo.isConnected
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}