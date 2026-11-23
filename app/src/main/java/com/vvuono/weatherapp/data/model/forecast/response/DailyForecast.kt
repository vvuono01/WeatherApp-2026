package com.vvuono.weatherapp.data.model.forecast.response

data class DailyForecast(
    val date: ForecastDate,
    val weatherCode: ForecastCode,
    val highTemperature: ForecastTemperature,
    val lowTemperature: ForecastTemperature
)

data class ForecastDate(
    val date: String,
    val format: String
)

data class ForecastCode(
    val code: Int,
    val label: String
)

data class ForecastTemperature(
    val temperature: Float,
    val unit: String
)
