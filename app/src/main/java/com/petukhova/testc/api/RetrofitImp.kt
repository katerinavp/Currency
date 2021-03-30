package com.petukhova.testc.api

import android.util.Log
import com.petukhova.testc.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImp {

    fun getApi(): Api {

        // конвертация объектовв json
        val converter = GsonConverterFactory.create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converter)
            .build()

        Log.i("Auth go", "da")
        return retrofit.create(Api::class.java)
    }

}