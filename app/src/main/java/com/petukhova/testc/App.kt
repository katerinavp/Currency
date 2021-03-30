package com.petukhova.testc

import android.app.Application
import android.text.format.DateFormat
import com.petukhova.testc.api.RetrofitService
import com.petukhova.testc.data.db.CurrencyDatabase
import com.petukhova.testc.data.prefs.LocalStorage
import com.petukhova.testc.data.repository.CurrencyRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

// В данном классе конфигурируем нашу прилу.
// Тут мы можем
// контролировать состояние приложения
// следить за использованием некоторых общих ресурсов, которые необходимы различным компонентам приложения
// определять поведение приложения в случае изменения конфигурации устройства
// определять поведение приложения в условиях дефицита памяти
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        checkLastUpdateData()
    }

    // При запуске чекаем дату последнего обновления из локального репозитория
    private fun checkLastUpdateData() {

        val localStorage = LocalStorage(instance)

        // Текущая дата в формате сервера
        // Тут нужно округлить минуты, иначе мы постоянно будем лезть на сервер за новыми данными
        val currentDate = DateFormat.format("yyyy-MM-dd-Thh:mm:00:z", Date()).toString()

        // Получаем данные о дате последнего обновлении
        val lastUpdate = localStorage.getLastDateUpdated()

        // На сервере данные обновляются в определенно еремя чекаем диапазон
        if (currentDate != lastUpdate) {

            // Идем на сервер за новыми данными
            val apiService = RetrofitService()
            val repository = CurrencyRepositoryImpl

            // Сохраняем в БД новые данные в отдельном потоке
            GlobalScope.launch(Dispatchers.Default) {

                // Несмотря на то что инстанс Room создан тут
                // Сохранять данные нужно через репозиторий
                repository.updateListCurrency()
            }

            // Сохраняем ноувю дату получения обновлений
            localStorage.saveLastDateUpdate(apiService.getLastUpdate())
        } else return

    }

    // Singleton instance
    // Т.к. данный класс создается 1 раз глобальные синглтоны инициализирует тут (такие как Room)
    companion object {
        var instance: App = null ?: App()
        val database: CurrencyDatabase by lazy { CurrencyDatabase.getInstance(instance) } // получаем БД
    }
}

