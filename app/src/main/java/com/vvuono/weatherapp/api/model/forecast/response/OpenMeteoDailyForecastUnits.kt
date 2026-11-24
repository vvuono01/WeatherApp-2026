package com.vvuono.weatherapp.api.model.forecast.response

import com.google.gson.annotations.SerializedName

data class OpenMeteoDailyForecastUnits(
    @SerializedName("time") val timezoneFormat: String,
    @SerializedName("weather_code") val weatherCodeLabel: String,
    @SerializedName("temperature_2m_max") val highTemperatureUnit: String,
    @SerializedName("temperature_2m_min") val lowTemperatureUnit: String,
)
