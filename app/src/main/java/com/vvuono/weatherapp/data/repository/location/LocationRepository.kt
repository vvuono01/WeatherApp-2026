package com.vvuono.weatherapp.data.repository.location

import io.reactivex.rxjava3.core.Single

interface LocationRepository {
    fun getLocationName(latitude: Double, longitude: Double): Single<String>
}
