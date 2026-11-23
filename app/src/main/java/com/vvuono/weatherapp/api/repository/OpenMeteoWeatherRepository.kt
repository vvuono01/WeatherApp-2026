package com.vvuono.weatherapp.api.repository

import com.vvuono.weatherapp.api.OpenMeteoApi
import com.vvuono.weatherapp.api.OpenMeteoApiService
import com.vvuono.weatherapp.api.converter.OpenMeteoConverter
import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastParams
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import com.vvuono.weatherapp.data.repository.WeatherRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class OpenMeteoWeatherRepository(
    api: OpenMeteoApi = OpenMeteoApi(),
    private val converter: OpenMeteoConverter = OpenMeteoConverter()
) : WeatherRepository {
    private val apiService: OpenMeteoApiService = api.service

    override fun getForecast(requestParams: RequestForecastParams): Single<Forecast> {
        return apiService.getForecast(
            requestParams.latitude,
            requestParams.longitude,
            requestParams.fields,
            requestParams.timezone,
            requestParams.days
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { converter.convertForecast(it) }
    }
}
