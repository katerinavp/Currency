package com.petukhova.testc.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.*

class LocalStorage(context: Context) {

    private companion object {
        const val PREFS_NAME = "ru.test"
        const val PREFS_LAST_UPDATED = "prefs_last_updated"
    }

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getLastDateUpdated(): String =
        prefs.value.getString(PREFS_LAST_UPDATED, "") ?: Calendar.getInstance().time.toString()

    fun saveLastDateUpdate(date: String) {
        prefs.value.edit { putString(PREFS_LAST_UPDATED, date) }
    }

}