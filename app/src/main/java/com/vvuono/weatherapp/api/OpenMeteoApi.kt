package com.vvuono.weatherapp.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OpenMeteoApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val service : OpenMeteoApiService by lazy {
        retrofit.create(OpenMeteoApiService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.open-meteo.com/v1/"
    }
}
