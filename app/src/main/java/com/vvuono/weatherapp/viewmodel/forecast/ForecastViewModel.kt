package com.vvuono.weatherapp.viewmodel.forecast

import androidx.lifecycle.ViewModel
import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastLocation
import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastParams
import com.vvuono.weatherapp.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val forecastParamsSubject = BehaviorSubject.create<RequestForecastParams>()

    val uiState: Flowable<ForecastUiState> = forecastParamsSubject
        .toFlowable(BackpressureStrategy.LATEST)
        .switchMap { params ->
            params.location?.let {
                weatherRepository.getForecast(params)
                    .mergeWith(weatherRepository.refreshForecast(params))
            } ?: Flowable.error(Exception("No location data was sent"))
        }
        .distinctUntilChanged()
        .map<ForecastUiState> { forecast -> ForecastUiState.Success(forecast) }
        .onErrorReturn { ForecastUiState.Error(it) }

    fun getForecastData(location: RequestForecastLocation?) {
        forecastParamsSubject.onNext(
            RequestForecastParams(
                location,
                FORECAST_FIELDS,
                FORECAST_TIMEZONE,
                FORECAST_DAYS
            )
        )
    }

    companion object {
        private const val FORECAST_FIELD_WEATHER_CODE = "weather_code"
        private const val FORECAST_FIELD_HIGH_TEMP = "temperature_2m_max"
        private const val FORECAST_FIELD_LOW_TEMP = "temperature_2m_min"

        private val FORECAST_FIELDS = listOf(
            FORECAST_FIELD_WEATHER_CODE,
            FORECAST_FIELD_HIGH_TEMP,
            FORECAST_FIELD_LOW_TEMP,
        )

        private const val FORECAST_TIMEZONE = "auto"
        private const val FORECAST_DAYS = 7
    }
}
