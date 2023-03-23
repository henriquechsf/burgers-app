package com.example.burgershub.di

import com.example.burgershub.data.api.ServiceApi
import com.example.burgershub.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun providesServiceApi(serviceProvider: ServiceProvider): ServiceApi {
        return serviceProvider.createService(ServiceApi::class.java)
    }
}