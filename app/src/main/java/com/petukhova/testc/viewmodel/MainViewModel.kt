package com.petukhova.testc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petukhova.testc.data.db.entity.Currency
import com.petukhova.testc.data.repository.CurrencyRepository
import com.petukhova.testc.data.repository.CurrencyRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repo: CurrencyRepository = CurrencyRepositoryImpl
    private val liveData = MutableLiveData<List<Currency>>()

    fun geCurrencyLiveData(): LiveData<List<Currency>> = liveData

    init {
        updateData()
    }

    fun updateData() {
        viewModelScope.launch(Dispatchers.Default) {
            liveData.postValue(repo.getListCurrency())
        }
    }


}
