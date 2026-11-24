package com.vvuono.weatherapp.viewmodel.forecast

import com.vvuono.weatherapp.data.model.forecast.response.Forecast

sealed interface ForecastUiState {
    object Loading : ForecastUiState
    data class Success(val forecast: Forecast) : ForecastUiState
    data class Error(val error: Throwable? = null) : ForecastUiState
}
