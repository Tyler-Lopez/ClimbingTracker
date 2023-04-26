package com.climbingtrackerapp.di

import android.content.Context
import com.climbingtrackerapp.data.repository.RecordRepositoryImpl
import com.climbingtrackerapp.domain.repository.RecordRepository
import com.climbingtrackerapp.util.climbingGrade.YosemiteListFactory
import com.climbingtrackerapp.util.climbingGrade.YosemiteListFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideYosemiteListFactory(): YosemiteListFactory = YosemiteListFactoryImpl()

    @Provides
    fun provideRecordRepository(
        @ApplicationContext context: Context
    ): RecordRepository = RecordRepositoryImpl(context = context)
}
