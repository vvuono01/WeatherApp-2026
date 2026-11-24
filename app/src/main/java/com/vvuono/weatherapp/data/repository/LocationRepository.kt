package com.vvuono.weatherapp.data.repository

import io.reactivex.rxjava3.core.Single

interface LocationRepository {
    fun getLocationName(latitude: Double, longitude: Double): Single<String>
}
