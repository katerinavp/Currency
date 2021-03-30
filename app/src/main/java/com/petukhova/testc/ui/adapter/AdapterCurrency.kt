package com.petukhova.testc.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.petukhova.testc.databinding.RecyclerviewItemBinding
import com.petukhova.testc.data.db.entity.Currency

class AdapterCurrency : ListAdapter<Currency, AdapterCurrency.CurrencyViewHolder>(ModelCurrencyDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
            CurrencyViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CurrencyViewHolder(private val binding: RecyclerviewItemBinding, ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            with(binding) {
                ticker.text = currency.charCode
                nameCurrency.text = currency.name
                value.text = currency.value.toString()
            }
        }
    }

}
