package com.vvuono.weatherapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {
    var weatherUiState: String by mutableStateOf("")

    init {
        getWeatherData()
    }

    fun getWeatherData() {
        weatherUiState = "Get weather response"
    }
}
