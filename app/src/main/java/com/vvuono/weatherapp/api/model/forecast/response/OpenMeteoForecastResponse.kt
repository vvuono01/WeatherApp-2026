package com.vvuono.weatherapp.api.model.forecast.response

import com.google.gson.annotations.SerializedName

data class OpenMeteoForecastResponse(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("generationtime_ms") val generationTimeMs: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Int,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerializedName("elevation") val elevation: Float,
    @SerializedName("daily_units") val dailyUnits: OpenMeteoDailyForecastUnits,
    @SerializedName("daily") val dailyValues: OpenMeteoDailyForecastValues
)

data class OpenMeteoDailyForecastUnits(
    @SerializedName("time") val timezoneFormat: String,
    @SerializedName("weather_code") val weatherCodeLabel: String,
    @SerializedName("temperature_2m_max") val highTemperatureUnit: String,
    @SerializedName("temperature_2m_min") val lowTemperatureUnit: String,
)

data class OpenMeteoDailyForecastValues(
    @SerializedName("time") val dates: List<String>,
    @SerializedName("weather_code") val weatherCodes: List<Int>,
    @SerializedName("temperature_2m_max") val highTemperatures: List<Float>,
    @SerializedName("temperature_2m_min") val lowTemperatures: List<Float>,
)
