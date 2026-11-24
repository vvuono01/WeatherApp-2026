package com.vvuono.weatherapp.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvuono.weatherapp.R
import com.vvuono.weatherapp.data.model.forecast.response.DailyForecast
import com.vvuono.weatherapp.ui.theme.WeatherTheme
import java.text.DecimalFormat

@Composable
fun DailyForecastCard(
    dailyForecast: DailyForecast,
    modifier: Modifier = Modifier
) {
    val decimalFormat = DecimalFormat("#.##")
    val highTempString = stringResource(R.string.daily_forecast_temp,
        decimalFormat.format(dailyForecast.highTemperature.temperature),
        dailyForecast.highTemperature.unit
    )
    val lowTempString = stringResource(R.string.daily_forecast_temp,
        decimalFormat.format(dailyForecast.lowTemperature.temperature),
        dailyForecast.lowTemperature.unit
    )

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = modifier.padding(16.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(
                        dailyForecast.condition.stringResource(LocalContext.current)
                    ),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = modifier.weight(1f)
                        .padding(end = 16.dp)
                )
                Text(
                    text = dailyForecast.date,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier.padding(horizontal = 0.dp, vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(
                    R.string.daily_forecast_temp_range,
                    highTempString, lowTempString
                ),
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DailyForecastCardPreview() {
    WeatherTheme {
        DailyForecastCard(DailyForecast.PREVIEW)
    }
}
