package com.vvuono.weatherapp.viewmodel.forecast

import com.vvuono.weatherapp.data.model.forecast.response.Forecast

sealed interface ForecastUiState {
    object Loading : ForecastUiState
    data class Success(val forecast: Forecast) : ForecastUiState
    object Error : ForecastUiState
}
