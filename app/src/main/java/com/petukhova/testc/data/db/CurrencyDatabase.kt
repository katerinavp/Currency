package com.petukhova.testc.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.petukhova.testc.data.db.dao.CurrencyDao
import com.petukhova.testc.data.db.entity.Currency
import com.petukhova.testc.util.Constants.DB_NAME

// Храним валюты
// По сути нам нужна только одна сущность для хранения в БД
@Database(entities = [Currency::class], version = 1)// Перечисляем в entities, какие классы будут использоваться для создания таблиц.
abstract class CurrencyDatabase : RoomDatabase() {// Описываем абстрактные методы для получения объектов интерфейса CurrencyDao, которые вам понадобятся

    // DAO
    abstract fun currencyDao(): CurrencyDao

    // DB
    // Настройку БД лучше делать в классе БД
    //Класс, описывающий саму базу данных. Достаточно одной строчки внутри класса, а всё остальное,
    // что внутри объекта companion, добавлено мной для реализации синглтона (чтобы при запросе базы больше одного объекта базы не создавалось)
    // и двух апдейтов базы
    // Сопутствующий объект для получения базы данных (фактически синглтон). Можно не использовать.
    companion object {
        fun getInstance(context: Context): CurrencyDatabase {
            return Room
                .databaseBuilder(context, CurrencyDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()   // Автоматическая миграция, в случае изменения Entity
                                                    // Если при запуске приложения Room увидит, что
                                                    // необходима миграция, то он просто пересоздаст
                                                    // базу в соответствии с новой структурой Entity классов и все данные пропадут.

            //  .allowMainThreadQueries()           // Разрешить запросы в UI потоке
                .build()
        }

    }

}