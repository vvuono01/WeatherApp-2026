package com.vvuono.weatherapp.api.converter

import com.vvuono.weatherapp.api.model.forecast.response.OpenMeteoForecastResponse
import com.vvuono.weatherapp.data.model.forecast.response.DailyForecast
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import com.vvuono.weatherapp.data.model.forecast.response.ForecastCode
import com.vvuono.weatherapp.data.model.forecast.response.ForecastDate
import com.vvuono.weatherapp.data.model.forecast.response.ForecastLocation
import com.vvuono.weatherapp.data.model.forecast.response.ForecastTemperature
import com.vvuono.weatherapp.data.model.forecast.response.ForecastTimezone
import javax.inject.Inject

class OpenMeteoConverter @Inject constructor() {
    fun convertForecast(response: OpenMeteoForecastResponse): Forecast {
        return Forecast(
            location = convertLocation(response),
            generationTimeMs = response.generationTimeMs,
            utcOffsetSeconds = response.utcOffsetSeconds,
            timezone = convertTimezone(response),
            elevation = response.elevation,
            dailyForecasts = convertDailyForecasts(response)
        )
    }

    fun convertLocation(response: OpenMeteoForecastResponse): ForecastLocation {
        return convertLocation(response.latitude, response.longitude)
    }

    private fun convertLocation(latitude: Double, longitude: Double): ForecastLocation {
        return ForecastLocation(latitude, longitude)
    }

    fun convertTimezone(response: OpenMeteoForecastResponse): ForecastTimezone {
        return convertTimezone(response.timezone, response.timezoneAbbreviation)
    }

    private fun convertTimezone(timezone: String, abbreviation: String): ForecastTimezone {
        return ForecastTimezone(timezone, abbreviation)
    }

    fun convertDailyForecasts(response: OpenMeteoForecastResponse): List<DailyForecast> {
        return response.dailyValues.dates.mapIndexed { index, date ->
            DailyForecast(
                date = convertDate(date, response.dailyUnits.timezoneFormat),
                weatherCode = convertCode(
                    response.dailyValues.weatherCodes[index],
                    response.dailyUnits.weatherCodeLabel
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

    private fun convertDate(date: String, format: String): ForecastDate {
        return ForecastDate(date, format)
    }

    private fun convertCode(code: Int, label: String): ForecastCode {
        return ForecastCode(code, label)
    }

    private fun convertTemperature(temperature: Float, unit: String): ForecastTemperature {
        return ForecastTemperature(temperature, unit)
    }
}
