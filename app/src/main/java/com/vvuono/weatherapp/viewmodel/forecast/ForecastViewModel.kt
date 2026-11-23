package com.vvuono.weatherapp.viewmodel.forecast

import androidx.lifecycle.ViewModel
import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastParams
import com.vvuono.weatherapp.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val forecastParamsSubject = BehaviorSubject.create<RequestForecastParams>()

    val uiState: Observable<ForecastUiState> = forecastParamsSubject
        .flatMapSingle {
            weatherRepository.getForecast(it)
        }
        .map<ForecastUiState> { forecast -> ForecastUiState.Success(forecast) }
        .onErrorReturn { ForecastUiState.Error }

    init {
        val latitude = 40.0612674 // TODO: Get from device location
        val longitude = -75.3420312 // TODO: Get from device location
        getForecastData(latitude, longitude)
    }

    fun getForecastData(latitude: Double, longitude: Double) {
        forecastParamsSubject.onNext(
            RequestForecastParams(
                latitude,
                longitude,
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
