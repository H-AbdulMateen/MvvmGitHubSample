package com.iislex.mvvmgithubsample.di

import com.iislex.mvvmgithubsample.network.RetroService
import com.iislex.mvvmgithubsample.network.model.UserDtoMapper
import com.iislex.mvvmgithubsample.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetroService(): RetroService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetroService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDtoMapper(): UserDtoMapper{
        return UserDtoMapper()
    }
}