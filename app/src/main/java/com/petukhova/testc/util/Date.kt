package com.petukhova.testc.util

import android.text.format.DateFormat
import java.util.*

// Дата вида 2021-03-26T11:30:00+03:00
fun Date.toServerFormat(): String {
    return DateFormat.format("yyyy-MM-dd-W:T:hh:mm:ss:z", Date()).toString()
}
