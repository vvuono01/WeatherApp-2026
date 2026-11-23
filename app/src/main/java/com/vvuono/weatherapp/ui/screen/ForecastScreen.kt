package com.vvuono.weatherapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.vvuono.weatherapp.R
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import com.vvuono.weatherapp.viewmodel.forecast.ForecastViewModel
import com.vvuono.weatherapp.viewmodel.forecast.ForecastUiState

@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.subscribeAsState(ForecastUiState.Loading)
    when (val state = uiState) {
        is ForecastUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is ForecastUiState.Success -> DisplayScreen(state.forecast, modifier = Modifier.fillMaxSize())
        is ForecastUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Text(stringResource(R.string.weather_loading))
        }
    }
}

@Composable
private fun DisplayScreen(result: Forecast?, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = result?.toString() ?: stringResource(R.string.forecast_data_empty))
    }
}

@Composable
private fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(stringResource(R.string.forecast_data_error))
    }
}
