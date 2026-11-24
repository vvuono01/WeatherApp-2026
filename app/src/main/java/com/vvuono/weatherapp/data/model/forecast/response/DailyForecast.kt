package com.vvuono.weatherapp.data.model.forecast.response

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class DailyForecast(
    val date: String,
    val condition: WeatherCondition,
    val highTemperature: ForecastTemperature,
    val lowTemperature: ForecastTemperature
) {
    companion object {
        val PREVIEW = DailyForecast(
            date = "2000-1-1",
            condition = WeatherCondition.UNKNOWN,
            highTemperature = ForecastTemperature(70f, "°C"),
            lowTemperature = ForecastTemperature(40f, "°C"),
        )
    }
}

class DailyForecastConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<DailyForecast> {
        if (value == null) return emptyList()
        val listType = object : TypeToken<List<DailyForecast>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<DailyForecast>?): String {
        return gson.toJson(list ?: emptyList<DailyForecast>())
    }
}
