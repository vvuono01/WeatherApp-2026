package com.vvuono.weatherapp.api.openmeteo.di

import com.vvuono.weatherapp.api.openmeteo.repository.forecast.OpenMeteoForecastRepository
import com.vvuono.weatherapp.data.repository.forecast.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OpenMeteoRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindForecastRepository(
        openMeteoForecastRepository: OpenMeteoForecastRepository
    ): ForecastRepository
}
