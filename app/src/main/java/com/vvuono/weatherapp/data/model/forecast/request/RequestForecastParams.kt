package com.vvuono.weatherapp.data.model.forecast.request

data class RequestForecastParams(
    val latitude: Double,
    val longitude: Double,
    val fields: List<String>,
    val timezone: String,
    val days: Int
)
