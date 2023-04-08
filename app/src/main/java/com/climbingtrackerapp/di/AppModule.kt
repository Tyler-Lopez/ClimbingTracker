package com.climbingtrackerapp.di

import com.climbingtrackerapp.util.climbingGrade.YosemiteListFactory
import com.climbingtrackerapp.util.climbingGrade.YosemiteListFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideYosemiteListFactory(): YosemiteListFactory = YosemiteListFactoryImpl()
}
