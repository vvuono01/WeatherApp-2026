package com.vvuono.weatherapp.data.repository

import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastParams
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import io.reactivex.rxjava3.core.Single

interface ForecastRepository {
    fun getForecast(requestParams: RequestForecastParams): Single<Forecast>
}
