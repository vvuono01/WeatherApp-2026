package com.vvuono.weatherapp.data.di

import com.vvuono.weatherapp.data.repository.CachedWeatherRepository
import com.vvuono.weatherapp.data.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CachedWeatherRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        cachedWeatherRepository: CachedWeatherRepository
    ): WeatherRepository
}
