package com.vvuono.weatherapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    weatherUiState: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    ResultScreen(weatherUiState, modifier.padding(top = contentPadding.calculateTopPadding()))
}
