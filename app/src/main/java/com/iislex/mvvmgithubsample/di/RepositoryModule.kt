package com.iislex.mvvmgithubsample.di

import com.iislex.mvvmgithubsample.network.RetroService
import com.iislex.mvvmgithubsample.network.model.UserDtoMapper
import com.iislex.mvvmgithubsample.repository.UserRepository
import com.iislex.mvvmgithubsample.repository.UserRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @ViewModelScoped
    @Provides
    fun provideUserRepository(retroService: RetroService,
    dtoMapper: UserDtoMapper): UserRepository{
        return UserRepository_Impl(retroService = retroService,
        dtoMapper = dtoMapper)
    }
}