package com.vvuono.weatherapp.api.openmeteo.repository.forecast

import com.vvuono.weatherapp.api.openmeteo.OpenMeteoApi
import com.vvuono.weatherapp.api.openmeteo.converter.OpenMeteoConverter
import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastParams
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import com.vvuono.weatherapp.data.model.forecast.response.ForecastLocation
import com.vvuono.weatherapp.data.repository.forecast.ForecastRepository
import com.vvuono.weatherapp.data.repository.location.LocationRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class OpenMeteoForecastRepository @Inject constructor(
    private val api: OpenMeteoApi,
    private val converter: OpenMeteoConverter,
    private val locationRepository: LocationRepository,
) : ForecastRepository {
    override fun getForecast(requestParams: RequestForecastParams): Single<Forecast> {
        val location = requestParams.location!!
        return api.getForecast(
            location.latitude,
            location.longitude,
            requestParams.fields,
            requestParams.timezone,
            requestParams.days
        )
            .subscribeOn(Schedulers.io())
            .flatMap { response ->
                locationRepository.getLocationName(location.latitude, location.longitude)
                    .map { locationName ->
                        Forecast(
                            location = ForecastLocation(
                                location.latitude,
                                location.longitude,
                                locationName
                            ),
                            dailyForecasts = converter.convertDailyForecasts(response)
                        )
                    }
            }
    }
}
