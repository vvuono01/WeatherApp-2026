package com.vvuono.weatherapp.api.di

import com.vvuono.weatherapp.api.repository.OpenMeteoWeatherRepository
import com.vvuono.weatherapp.data.repository.WeatherRepository
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
    abstract fun bindWeatherRepository(
        openMeteoWeatherRepository: OpenMeteoWeatherRepository
    ): WeatherRepository
}
