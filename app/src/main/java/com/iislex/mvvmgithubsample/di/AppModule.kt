package com.iislex.mvvmgithubsample.di

import android.content.Context
import com.iislex.mvvmgithubsample.presentation.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(app: Context): MyApplication{
        return app as MyApplication
    }
}