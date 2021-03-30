package com.petukhova.testc.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petukhova.testc.data.db.entity.Currency

@Dao
interface CurrencyDao{

    // Для текущей задачи нам нужны только две функции
    // Записать данные и получить

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveListCurrency(modelResponse: Currency)

    @Query("SELECT * FROM Currency")
    fun getListCurrency(): List<Currency>

}