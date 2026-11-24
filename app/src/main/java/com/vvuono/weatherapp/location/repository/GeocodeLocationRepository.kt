package com.vvuono.weatherapp.location.repository

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import com.vvuono.weatherapp.R
import com.vvuono.weatherapp.data.repository.LocationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Locale
import javax.inject.Inject

class GeocodeLocationRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationRepository {
    override fun getLocationName(latitude: Double, longitude: Double): Single<String> {
        return Single.create { emitter ->
            val geocoder = Geocoder(context, Locale.getDefault())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocation(latitude, longitude, 1,
                    object : Geocoder.GeocodeListener {
                        override fun onGeocode(addresses: List<Address>) {
                            getLocationNameFromAddress(latitude, longitude, addresses, emitter)
                        }

                        override fun onError(errorMessage: String?) {
                            if (!emitter.isDisposed) {
                                emitter.onError(Exception(
                                    errorMessage ?: "Unknown coordinates ($latitude, $longitude)"
                                ))
                            }
                        }
                    }
                )
            } else {
                try {
                    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                    getLocationNameFromAddress(latitude, longitude, addresses, emitter)
                } catch (e: Exception) {
                    if (!emitter.isDisposed) {
                        emitter.onError(e)
                    }
                }
            }
        }
            .onErrorReturn { context.getString(R.string.forecast_location_unknown) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getLocationNameFromAddress(
        latitude: Double,
        longitude: Double,
        addresses: List<Address?>?,
        emitter: SingleEmitter<String>
    ) {
        if (emitter.isDisposed) return
        val address = addresses?.firstOrNull()
        val name = address?.let {
            context.getString(
                R.string.forecast_location_format,
                (it.locality ?: it.subAdminArea), it.adminArea
            )
        }

        name?.let { emitter.onSuccess(it) } ?: emitter.onError(
            Exception("No proper name found for coordinates ($latitude, $longitude)")
        )
    }
}
