package com.vvuono.weatherapp.data.model.forecast.response

data class Forecast(
    val location: ForecastLocation,
    val generationTimeMs: Double,
    val utcOffsetSeconds: Int,
    val timezone: ForecastTimezone,
    val elevation: Float,
    val dailyForecasts: List<DailyForecast>,
)

data class ForecastLocation(
    val latitude: Double,
    val longitude: Double
)

data class ForecastTimezone(
    val timezone: String,
    val abbreviation: String
)
