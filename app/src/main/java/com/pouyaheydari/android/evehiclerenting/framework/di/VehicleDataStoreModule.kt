package com.pouyaheydari.android.evehiclerenting.framework.di

import com.pouyaheydari.android.core.data.SelectedVehicleDataStore
import com.pouyaheydari.android.evehiclerenting.framework.InMemorySelectedVehicleDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class VehicleDataStoreModule {

    @Provides
    @Singleton
    fun provideSelectedVehicleDataStore(): SelectedVehicleDataStore {
        return InMemorySelectedVehicleDataStore()
    }
}