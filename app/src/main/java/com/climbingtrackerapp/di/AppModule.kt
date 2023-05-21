package com.climbingtrackerapp.di

import com.climbingtrackerapp.domain.model.climbListFactory.ClimbGradeSpecificationListFactory
import com.climbingtrackerapp.domain.model.climbListFactory.ClimbSpecificationGradeSpecificationFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideYosemiteListFactory(): ClimbGradeSpecificationListFactory = ClimbSpecificationGradeSpecificationFactoryImpl()
}
