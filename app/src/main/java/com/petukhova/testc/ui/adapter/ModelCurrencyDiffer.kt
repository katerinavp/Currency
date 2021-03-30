package com.petukhova.testc.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.petukhova.testc.api.model.ModelResponse
import com.petukhova.testc.data.db.entity.Currency

object ModelCurrencyDiffer : DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
        oldItem.value == newItem.value

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
        oldItem == newItem
}
