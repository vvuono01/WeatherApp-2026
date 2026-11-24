package com.vvuono.weatherapp.data.model.forecast.response

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.TypeConverters

@Entity(
    tableName = "forecasts",
    primaryKeys = ["latitude", "longitude"]
)
@TypeConverters(DailyForecastConverter::class)
data class Forecast(
    @Embedded val location: ForecastLocation,
    val dailyForecasts: List<DailyForecast>
)
