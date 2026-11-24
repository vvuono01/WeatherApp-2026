package com.vvuono.weatherapp.ui.screen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvuono.weatherapp.R
import com.vvuono.weatherapp.data.model.forecast.response.DailyForecast
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import com.vvuono.weatherapp.data.model.forecast.response.ForecastLocation
import com.vvuono.weatherapp.ui.elements.DailyForecastCard
import com.vvuono.weatherapp.ui.theme.WeatherTheme

@Composable
fun ForecastDisplayView(
    forecast: Forecast,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(innerPadding)
            .padding(8.dp)
    ) {
        Text(
            text = forecast.location.name,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier.padding(4.dp))
        LazyColumn(
            modifier = modifier.fillMaxWidth()
        ) {
            items(forecast.dailyForecasts) {
                DailyForecastCard(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastDisplayViewPreview() {
    WeatherTheme {
        ForecastDisplayView(
            forecast = Forecast(
                location = ForecastLocation(0.0, 0.0, stringResource(R.string.forecast_location_unknown)),
                dailyForecasts = listOf(
                    DailyForecast.PREVIEW,
                    DailyForecast.PREVIEW,
                    DailyForecast.PREVIEW,
                    DailyForecast.PREVIEW,
                    DailyForecast.PREVIEW,
                    DailyForecast.PREVIEW,
                    DailyForecast.PREVIEW,
                ),
            ),
            innerPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
