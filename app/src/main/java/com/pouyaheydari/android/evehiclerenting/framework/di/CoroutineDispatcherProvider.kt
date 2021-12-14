package com.pouyaheydari.android.evehiclerenting.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatcherProvider {

    @Provides
    @Singleton
    fun providesIOCoroutineDispatcher() = Dispatchers.IO
}