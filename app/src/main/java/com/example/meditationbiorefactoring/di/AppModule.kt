package com.example.meditationbiorefactoring.di

import com.example.meditationbiorefactoring.feature_bio.data.repository.BpmRepositoryImpl
import com.example.meditationbiorefactoring.feature_bio.data.repository.BrpmRepositoryImpl
import com.example.meditationbiorefactoring.feature_bio.domain.repository.BpmRepository
import com.example.meditationbiorefactoring.feature_bio.domain.repository.BrpmRepository
import com.example.meditationbiorefactoring.feature_bio.util.BreathAnalyzerCore
import com.example.meditationbiorefactoring.feature_bio.util.PpgAnalyzerCore
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
    fun providePpgAnalyzer(): PpgAnalyzerCore = PpgAnalyzerCore()

    @Provides
    @Singleton
    fun provideBreathAnalyzer(): BreathAnalyzerCore = BreathAnalyzerCore()

    @Provides
    @Singleton
    fun provideBpmRepository(
        analyzer: PpgAnalyzerCore
    ): BpmRepository = BpmRepositoryImpl(analyzer)

    @Provides
    @Singleton
    fun provideBrpmRepository(
        analyzer: BreathAnalyzerCore
    ): BrpmRepository = BrpmRepositoryImpl(analyzer)
}