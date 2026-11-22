package com.vvuono.weatherapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vvuono.weatherapp.ui.theme.WeatherTheme
import com.vvuono.weatherapp.R

@Composable
fun ResultScreen(result: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = result)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    WeatherTheme {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}
