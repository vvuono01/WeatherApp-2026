package com.vvuono.weatherapp.api

import com.vvuono.weatherapp.api.model.forecast.response.OpenMeteoForecastResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoApiService {
    @GET("forecast")
    fun getForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") fields: List<String>,
        @Query("timezone") timezone: String,
        @Query("forecast_days") days: Int
    ): Single<OpenMeteoForecastResponse>
}
