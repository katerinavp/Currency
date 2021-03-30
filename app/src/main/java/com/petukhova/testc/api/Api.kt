package com.petukhova.testc.api

import com.petukhova.testc.api.model.ModelResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("daily_json.js")
   suspend fun getCurrency(): ModelResponse

}