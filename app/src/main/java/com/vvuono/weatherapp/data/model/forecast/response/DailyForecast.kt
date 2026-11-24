package com.vvuono.weatherapp.data.model.forecast.response

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

data class ForecastTemperature(
    val temperature: Float,
    val unit: String
)
