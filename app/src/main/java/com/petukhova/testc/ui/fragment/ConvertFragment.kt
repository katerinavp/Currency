package com.petukhova.testc.ui.fragment

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.petukhova.testc.R
import com.petukhova.testc.data.db.entity.Currency
import com.petukhova.testc.databinding.FragmentConvertBinding
import com.petukhova.testc.viewmodel.MainViewModel
import splitties.toast.toast
import java.lang.Double
import java.text.DecimalFormat


class ConvertFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentConvertBinding.inflate(LayoutInflater.from(requireContext()))
    }
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this)[MainViewModel::class.java]
    }
    private var valueDouble = 0.0

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        getActivity()?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        viewModel.geCurrencyLiveData().observe(requireActivity(), {
            initSpinner(it)

        })


        binding.btnConvert.setOnClickListener {
            if (!binding.txtInputRub.text.toString().isEmpty()) {
                doConvert()
            } else {
                toast(getString(R.string.warning_null_input_rub))
            }
        }

        return binding.root
    }

    fun initSpinner(list: List<Currency>) {
        println(list.map { it.charCode })

        val adapterCharCode = ArrayAdapter(
            requireActivity(),
            R.layout.support_simple_spinner_dropdown_item,
            list.map { it.charCode })
        binding.spinnerTicker.setAdapter(adapterCharCode) // заполняем спиннер данными

        binding.spinnerTicker.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener { // выбираем валюту в спиннере
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    for (element in list) {
                        if (selectedItem.equals(element.charCode)) {

                            valueDouble = Double.parseDouble(element.value.toString())

                        }
                        println("данные $valueDouble")
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
    }


    fun doConvert() {
        val input: kotlin.Double = binding.txtInputRub.text.toString().toDouble()
        println("данные input $input")

        val result = input / valueDouble
        val df = DecimalFormat("#.##") //округляем до 2-х знаков
        val resultf = df.format(result)
        println("result $resultf")
        binding.txtResultConvert.setText("$resultf")
    }
}







