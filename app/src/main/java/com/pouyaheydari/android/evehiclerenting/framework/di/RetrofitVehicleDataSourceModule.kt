package com.pouyaheydari.android.evehiclerenting.framework.di

import com.pouyaheydari.android.core.data.VehicleDataSource
import com.pouyaheydari.android.evehiclerenting.framework.RetrofitVehicleDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitVehicleDataSourceModule {

    @Singleton
    @Provides
    fun provideVehicleDataSource(retrofit: Retrofit): VehicleDataSource =
        retrofit.create(RetrofitVehicleDataSource::class.java)

}