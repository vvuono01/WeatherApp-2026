package com.vvuono.weatherapp.data.model.forecast.response

data class Forecast(
    val location: ForecastLocation,
    val dailyForecasts: List<DailyForecast>,
)

data class ForecastLocation(
    val latitude: Double,
    val longitude: Double,
    val name: String
)
