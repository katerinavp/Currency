package com.petukhova.testc.data.repository


import com.petukhova.testc.data.db.entity.Currency

interface CurrencyRepository {

    fun getListCurrency(): List<Currency>

    fun saveListCurrency(list: List<Currency>)

}

