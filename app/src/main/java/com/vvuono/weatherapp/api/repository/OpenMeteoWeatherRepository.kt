package com.vvuono.weatherapp.api.repository

import com.vvuono.weatherapp.api.OpenMeteoApi
import com.vvuono.weatherapp.api.converter.OpenMeteoConverter
import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastParams
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import com.vvuono.weatherapp.data.repository.WeatherRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class OpenMeteoWeatherRepository @Inject constructor(
    private val api: OpenMeteoApi,
    private val converter: OpenMeteoConverter
) : WeatherRepository {
    override fun getForecast(requestParams: RequestForecastParams): Single<Forecast> {
        return api.getForecast(
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
