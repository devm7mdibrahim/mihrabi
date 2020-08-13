package com.devm7mdibrahim.mihrabi.di

import android.content.Context
import androidx.room.Room
import com.devm7mdibrahim.mihrabi.model.local.AppDatabase
import com.devm7mdibrahim.mihrabi.model.local.quran.AyahLocalDataSource
import com.devm7mdibrahim.mihrabi.model.local.quran.AyahLocalDataSourceImpl
import com.devm7mdibrahim.mihrabi.model.local.quran.AyahDAO
import com.devm7mdibrahim.mihrabi.model.local.azkar.AzkarDao
import com.devm7mdibrahim.mihrabi.model.local.azkar.AzkarLocalDataSource
import com.devm7mdibrahim.mihrabi.model.local.azkar.AzkarLocalDataSourceImpl
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.FadlElsalahDao
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.FadlElsalahLocalDataSource
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.FadlElsalahLocalDataSourceImpl
import com.devm7mdibrahim.mihrabi.model.local.fiqh.FiqhDao
import com.devm7mdibrahim.mihrabi.model.local.surah.SurahDao
import com.devm7mdibrahim.mihrabi.model.local.fiqh.FiqhLocalDataSource
import com.devm7mdibrahim.mihrabi.model.local.fiqh.FiqhLocalDataSourceImpl
import com.devm7mdibrahim.mihrabi.model.local.surah.SurahLocalDataSource
import com.devm7mdibrahim.mihrabi.model.local.surah.SurahLocalDataSourceImpl
import com.devm7mdibrahim.mihrabi.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideAyahDao(database: AppDatabase): AyahDAO = database.ayahDAO()

    @Singleton
    @Provides
    fun provideSurahDao(database: AppDatabase): SurahDao = database.surahDao()

    @Singleton
    @Provides
    fun provideAzkarDao(database: AppDatabase): AzkarDao = database.azkarDao()

    @Singleton
    @Provides
    fun provideFiqhDao(database: AppDatabase): FiqhDao = database.fiqhDao()

    @Singleton
    @Provides
    fun provideFadlElsalahDao(database: AppDatabase): FadlElsalahDao = database.fadlDao()


    @Singleton
    @Provides
    fun provideAyahLocalDataSource(ayahDAO: AyahDAO): AyahLocalDataSource = AyahLocalDataSourceImpl(ayahDAO)

    @Singleton
    @Provides
    fun provideAzkarLocalDataSource(azkarDao: AzkarDao): AzkarLocalDataSource = AzkarLocalDataSourceImpl(azkarDao)

    @Singleton
    @Provides
    fun provideFiqhLocalDataSource(fiqhDao: FiqhDao): FiqhLocalDataSource = FiqhLocalDataSourceImpl(fiqhDao)

    @Singleton
    @Provides
    fun provideFadlElsalahDataSource(fadlElsalahDao: FadlElsalahDao): FadlElsalahLocalDataSource = FadlElsalahLocalDataSourceImpl(fadlElsalahDao)

    @Singleton
    @Provides
    fun provideSurahLocalDataSource(surahDao: SurahDao): SurahLocalDataSource = SurahLocalDataSourceImpl(surahDao)
}