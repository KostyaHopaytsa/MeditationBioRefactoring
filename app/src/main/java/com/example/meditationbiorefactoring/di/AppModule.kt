package com.example.meditationbiorefactoring.di

import com.example.meditationbiorefactoring.feature_bio.data.repository.BpmRepositoryImpl
import com.example.meditationbiorefactoring.feature_bio.domain.repository.BpmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBpmRepository(): BpmRepository {
        return BpmRepositoryImpl()
    }
}