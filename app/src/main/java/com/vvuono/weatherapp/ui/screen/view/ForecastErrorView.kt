package com.vvuono.weatherapp.ui.screen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvuono.weatherapp.R
import com.vvuono.weatherapp.ui.theme.WeatherTheme

@Composable
fun ForecastErrorView(
    error: Throwable?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_error_outline_48),
            contentDescription = stringResource(R.string.forecast_data_error),
            modifier = Modifier.size(96.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.padding(8.dp))
        Text(
            text = stringResource(R.string.forecast_data_error),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = error?.message ?: stringResource(R.string.forecast_data_error_unknown),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastErrorViewPreview() {
    WeatherTheme {
        ForecastErrorView(
            error = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}
