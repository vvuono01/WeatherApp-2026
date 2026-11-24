package com.vvuono.weatherapp.data.repository

import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastParams
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    fun getForecast(requestParams: RequestForecastParams): Flowable<Forecast>
    fun refreshForecast(requestParams: RequestForecastParams): Completable
}
