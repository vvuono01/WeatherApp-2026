package com.vvuono.weatherapp.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.vvuono.weatherapp.data.model.forecast.request.RequestForecastLocation
import com.vvuono.weatherapp.ui.screen.view.ForecastDisplayView
import com.vvuono.weatherapp.ui.screen.view.ForecastErrorView
import com.vvuono.weatherapp.ui.screen.view.ForecastLoadingView
import com.vvuono.weatherapp.viewmodel.forecast.ForecastUiState
import com.vvuono.weatherapp.viewmodel.forecast.ForecastViewModel

@Composable
fun ForecastScreen(
    innerPadding: PaddingValues,
    viewModel: ForecastViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.subscribeAsState(ForecastUiState.Loading)

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val permissionGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (permissionGranted) {
            getCurrentLocation(fusedLocationClient) { location ->
                viewModel.getForecastData(location)
            }
        } else {
            viewModel.getForecastData(null)
        }
    }

    when (val state = uiState) {
        is ForecastUiState.Loading -> ForecastLoadingView(modifier = Modifier.fillMaxSize())
        is ForecastUiState.Error -> ForecastErrorView(error = state.error, modifier = Modifier.fillMaxSize())
        is ForecastUiState.Success -> ForecastDisplayView(
            forecast = state.forecast,
            innerPadding = innerPadding,
            modifier = Modifier.fillMaxWidth(),
        )
    }

    LaunchedEffect(Unit) {
        val hasCoarseLocation = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasCoarseLocation) {
            getCurrentLocation(fusedLocationClient) { location ->
                viewModel.getForecastData(location)
            }
        } else {
            locationPermissionLauncher.launch(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
            )
        }
    }
}

@SuppressLint("MissingPermission")
private fun getCurrentLocation(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationReceived: (RequestForecastLocation?) -> Unit
) {
    val cancellationTokenSource = CancellationTokenSource()
    fusedLocationClient.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        cancellationTokenSource.token
    ).addOnSuccessListener { location ->
        if (location != null) {
            onLocationReceived(
                RequestForecastLocation(location.latitude, location.longitude)
            )
        } else {
            // In rare situations, location might return null.
            // Fallback to getLastLocation if needed.
            fusedLocationClient.lastLocation
                .addOnSuccessListener { lastLoc ->
                    if (lastLoc != null) {
                        onLocationReceived(
                            RequestForecastLocation(lastLoc.latitude, lastLoc.longitude)
                        )
                    } else {
                        onLocationReceived(null)
                    }
                }
        }
    }
}
