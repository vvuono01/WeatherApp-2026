package com.vvuono.weatherapp.data.model.forecast.request

data class RequestForecastParams(
    val location: RequestForecastLocation?,
    val fields: List<String>,
    val timezone: String,
    val days: Int
)
