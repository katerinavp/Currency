package com.petukhova.testc.ui.fragment

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.petukhova.testc.databinding.FragmentMainBinding
import com.petukhova.testc.ui.adapter.AdapterCurrency
import com.petukhova.testc.util.replaceFragment
import com.petukhova.testc.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentMainBinding.inflate(LayoutInflater.from(requireContext()))
    }
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this)[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {

        val adapter = AdapterCurrency()
        binding.recyclerView.adapter = adapter

        viewModel.geCurrencyLiveData().observe(requireActivity(), {
            adapter.submitList(it)
        })

        getActivity()?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR)
        //загрузка данных по кнопке
        binding.btnUpdate.setOnClickListener {
            updateListCurrency()
        }

        binding.btnConvert.setOnClickListener {
            showConverterFragment()
        }

        return binding.root
    }


    private fun updateListCurrency() {
        binding.progressBar.isVisible = true
        viewModel.updateData()

        lifecycleScope.launch(Dispatchers.Main) {
            delay(3000L)
            binding.progressBar.isVisible = false
        }
    }

    private fun showConverterFragment() {
        this.replaceFragment(ConvertFragment())
    }

}
