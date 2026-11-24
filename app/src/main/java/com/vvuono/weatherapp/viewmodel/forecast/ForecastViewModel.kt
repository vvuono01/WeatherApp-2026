package com.vvuono.weatherapp.viewmodel.forecast

import androidx.lifecycle.ViewModel
import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastLocation
import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastParams
import com.vvuono.weatherapp.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val forecastParamsSubject = BehaviorSubject.create<RequestForecastParams>()

    val uiState: Observable<ForecastUiState> = forecastParamsSubject
        .flatMapSingle { params ->
            params.location?.let {
                weatherRepository.getForecast(params)
            } ?: Single.error(Exception("No location data was sent"))
        }
        .map<ForecastUiState> { forecast -> ForecastUiState.Success(forecast) }
        .onErrorReturn { ForecastUiState.Error }

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
