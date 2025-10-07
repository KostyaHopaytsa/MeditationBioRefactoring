package com.example.meditationbiorefactoring.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.meditationbiorefactoring.feature_bio.data.local.MeasurementDatabase
import com.example.meditationbiorefactoring.feature_bio.data.repository.BpmRepositoryImpl
import com.example.meditationbiorefactoring.feature_bio.data.repository.BrpmRepositoryImpl
import com.example.meditationbiorefactoring.feature_bio.data.repository.MeasurementRepositoryImpl
import com.example.meditationbiorefactoring.feature_bio.data.repository.SivRepositoryImpl
import com.example.meditationbiorefactoring.feature_bio.data.repository.StressAggregatorRepositoryImpl
import com.example.meditationbiorefactoring.feature_bio.domain.repository.BpmRepository
import com.example.meditationbiorefactoring.feature_bio.domain.repository.BrpmRepository
import com.example.meditationbiorefactoring.feature_bio.domain.repository.MeasurementRepository
import com.example.meditationbiorefactoring.feature_bio.domain.repository.SivRepository
import com.example.meditationbiorefactoring.feature_bio.domain.repository.StressAggregatorRepository
import com.example.meditationbiorefactoring.feature_bio.util.BreathAnalyzerCore
import com.example.meditationbiorefactoring.feature_bio.util.PpgAnalyzerCore
import com.example.meditationbiorefactoring.feature_bio.util.SivAnalyzerCore
import com.example.meditationbiorefactoring.feature_music.data.repository.MusicPlayerRepositoryImpl
import com.example.meditationbiorefactoring.feature_music.domain.repository.MusicPlayerRepository
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
    @Singleton
    fun providePpgAnalyzer(): PpgAnalyzerCore = PpgAnalyzerCore()

    @Provides
    @Singleton
    fun provideBreathAnalyzer(): BreathAnalyzerCore = BreathAnalyzerCore()

    @Provides
    @Singleton
    fun provideSivAnalyzer(): SivAnalyzerCore = SivAnalyzerCore()

    @Provides
    @Singleton
    fun provideBpmRepository(analyzer: PpgAnalyzerCore): BpmRepository {
        return BpmRepositoryImpl(analyzer)
    }

    @Provides
    @Singleton
    fun provideBrpmRepository(analyzer: BreathAnalyzerCore): BrpmRepository {
        return BrpmRepositoryImpl(analyzer)
    }

    @Provides
    @Singleton
    fun provideSivRepository(analyzer: SivAnalyzerCore): SivRepository {
        return SivRepositoryImpl(analyzer)
    }

    @Provides
    @Singleton
    fun provideMeasurementDatabase(app: Application): MeasurementDatabase {
        return Room.databaseBuilder(
            app,
            MeasurementDatabase::class.java,
            MeasurementDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMeasurementRepository(db: MeasurementDatabase): MeasurementRepository {
        return MeasurementRepositoryImpl(db.measurementDao)
    }

    @Provides
    @Singleton
    fun provideStressAggregatorRepository(
        measurementRepository: MeasurementRepository
    ): StressAggregatorRepository {
        return StressAggregatorRepositoryImpl(
            measurementRepository
        )
    }

    @Provides
    @Singleton
    fun provideMusicPlayerRepository(
        @ApplicationContext context: Context
    ): MusicPlayerRepository {
        return MusicPlayerRepositoryImpl(context)
    }
}