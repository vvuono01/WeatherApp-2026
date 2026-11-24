package com.vvuono.weatherapp.location.di

import com.vvuono.weatherapp.data.repository.LocationRepository
import com.vvuono.weatherapp.location.repository.GeocodeLocationRepository
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
