package com.vvuono.weatherapp.api.openmeteo.model.forecast.response

import com.google.gson.annotations.SerializedName

data class OpenMeteoDailyForecastValues(
    @SerializedName("time") val dates: List<String>,
    @SerializedName("weather_code") val weatherCodes: List<Int>,
    @SerializedName("temperature_2m_max") val highTemperatures: List<Float>,
    @SerializedName("temperature_2m_min") val lowTemperatures: List<Float>,
)
