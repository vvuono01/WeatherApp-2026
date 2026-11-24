package com.vvuono.weatherapp.api.converter

import com.vvuono.weatherapp.api.model.forecast.response.OpenMeteoForecastResponse
import com.vvuono.weatherapp.data.model.forecast.response.DailyForecast
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import com.vvuono.weatherapp.data.model.forecast.response.ForecastLocation
import com.vvuono.weatherapp.data.model.forecast.response.ForecastTemperature
import com.vvuono.weatherapp.data.model.forecast.response.WeatherCondition
import javax.inject.Inject

class OpenMeteoConverter @Inject constructor() {
    fun convertForecast(response: OpenMeteoForecastResponse, locationName: String): Forecast {
        return Forecast(
            location = convertLocation(response, locationName),
            dailyForecasts = convertDailyForecasts(response)
        )
    }

    fun convertLocation(response: OpenMeteoForecastResponse, locationName: String): ForecastLocation {
        return convertLocation(response.latitude, response.longitude, locationName)
    }

    private fun convertLocation(latitude: Double, longitude: Double, locationName: String): ForecastLocation {
        return ForecastLocation(latitude, longitude, locationName)
    }

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
