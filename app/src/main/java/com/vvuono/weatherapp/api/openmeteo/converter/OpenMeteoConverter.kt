package com.vvuono.weatherapp.api.openmeteo.converter

import com.vvuono.weatherapp.api.openmeteo.model.forecast.response.OpenMeteoForecastResponse
import com.vvuono.weatherapp.data.model.forecast.response.DailyForecast
import com.vvuono.weatherapp.data.model.forecast.response.ForecastTemperature
import com.vvuono.weatherapp.data.model.forecast.response.WeatherCondition
import javax.inject.Inject

class OpenMeteoConverter @Inject constructor() {
    fun convertDailyForecasts(response: OpenMeteoForecastResponse): List<DailyForecast> {
        return response.dailyValues.dates.mapIndexed { index, date ->
            DailyForecast(
                date = date,
                condition = convertCondition(
                    response.dailyValues.weatherCodes[index]
                ),
                highTemperature = convertTemperature(
                    response.dailyValues.highTemperatures[index],
                    response.dailyUnits.highTemperatureUnit
                ),
                lowTemperature = convertTemperature(
                    response.dailyValues.lowTemperatures[index],
                    response.dailyUnits.lowTemperatureUnit
                )
            )
        }
    }

    private fun convertCondition(code: Int): WeatherCondition {
        return WeatherCondition.fromCode(code)
    }

    private fun convertTemperature(temperature: Float, unit: String): ForecastTemperature {
        return ForecastTemperature(temperature, unit)
    }
}
