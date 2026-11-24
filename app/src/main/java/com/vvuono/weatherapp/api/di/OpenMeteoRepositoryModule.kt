package com.vvuono.weatherapp.api.di

import com.vvuono.weatherapp.api.repository.OpenMeteoForecastRepository
import com.vvuono.weatherapp.data.repository.ForecastRepository
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
