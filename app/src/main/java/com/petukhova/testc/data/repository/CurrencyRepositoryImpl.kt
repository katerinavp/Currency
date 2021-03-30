package com.petukhova.testc.data.repository

import com.petukhova.testc.App
import com.petukhova.testc.api.RetrofitService
import com.petukhova.testc.api.model.Currency.Companion.toFormatInDB
import com.petukhova.testc.data.db.entity.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object CurrencyRepositoryImpl : CurrencyRepository {

    private val apiService = RetrofitService()
    private var listCurrency: MutableList<Currency> = mutableListOf()
    private val currencyDAO = App.database.currencyDao()

    override fun getListCurrency(): List<Currency> {
        val list = currencyDAO.getListCurrency().toMutableList()
        listCurrency = list
        return listCurrency
    }

    override fun saveListCurrency(list: List<Currency>) {
        list.forEach { currency ->
            currencyDAO.saveListCurrency(currency)
        }

    }

    fun updateListCurrency() {
        GlobalScope.launch(Dispatchers.Default) {
            saveListCurrency(apiService.getListCurrency().toListDBCurrency())
        }
    }


    fun List<com.petukhova.testc.api.model.Currency>.toListDBCurrency(): List<Currency> {
        val list = mutableListOf<Currency>()
        this.forEach {
            list.add(it.toFormatInDB())
        }
        return list
    }

}