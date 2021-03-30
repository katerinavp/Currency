package com.petukhova.testc.api

import com.petukhova.testc.api.model.Currency

class RetrofitService {

    private val api: Api = RetrofitImp().getApi()

    // Под текущую задачу нам нужны 2 стуктуры
    private var listCurrency: MutableList<Currency> = mutableListOf()
    private var latestUpdate: String = ""

    private suspend fun updateData() {
        val response = api.getCurrency()

        // Сохраняем дату обновления
        latestUpdate = response.date
        listCurrency = response.valute.values.toMutableList()
    }

    fun getLastUpdate(): String = latestUpdate

    suspend fun getListCurrency(): List<Currency> = run {
        updateData()
        return listCurrency.toList()
    }

}
