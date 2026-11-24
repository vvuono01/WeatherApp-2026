package com.vvuono.weatherapp.data.repository

import com.vvuono.weatherapp.data.model.forecast.dao.ForecastDao
import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastParams
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class CachedWeatherRepository @Inject constructor(
    private val forecastRepository: ForecastRepository,
    private val forecastDao: ForecastDao,
) : WeatherRepository {
    override fun getForecast(requestParams: RequestForecastParams): Flowable<Forecast> {
        val location = requestParams.location!!
        return forecastDao.getForecast(location.latitude, location.longitude)
    }

    override fun refreshForecast(requestParams: RequestForecastParams): Completable {
        return forecastRepository.getForecast(requestParams)
            .flatMapCompletable { forecast ->
                forecastDao.insert(forecast)
            }
            .onErrorComplete()

    }
}
