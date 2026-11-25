package com.vvuono.weatherapp.api.geocode.di

import com.vvuono.weatherapp.api.geocode.repository.location.GeocodeLocationRepository
import com.vvuono.weatherapp.data.repository.location.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GeocodeRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindLocationRepository(
        geocodeLocationRepository: GeocodeLocationRepository
    ): LocationRepository
}
