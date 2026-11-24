package com.vvuono.weatherapp.data.model.forecast.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vvuono.weatherapp.data.model.forecast.response.Forecast
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecast: Forecast): Completable

    @Query("SELECT * FROM forecasts WHERE latitude = :latitude AND longitude = :longitude")
    fun getForecast(latitude: Double, longitude: Double): Flowable<Forecast>
}
