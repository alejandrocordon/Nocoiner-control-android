package com.natio21.nocoiner_control.di

import com.natio21.nocoiner_control.MinerApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Ejemplo: proveer Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.121/") // Ajusta tu IP/base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Aquí provees la implementación de MinerApiService
    @Provides
    @Singleton
    fun provideMinerApiService(retrofit: Retrofit): MinerApiService {
        return retrofit.create(MinerApiService::class.java)
    }
}
