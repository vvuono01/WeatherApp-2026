package com.vvuono.weatherapp.api.openmeteo.model.forecast.response

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
