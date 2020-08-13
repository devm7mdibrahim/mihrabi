package com.devm7mdibrahim.mihrabi.di

import com.devm7mdibrahim.mihrabi.model.local.azkar.AzkarLocalDataSource
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.FadlElsalahLocalDataSource
import com.devm7mdibrahim.mihrabi.model.local.fiqh.FiqhLocalDataSource
import com.devm7mdibrahim.mihrabi.model.local.quran.AyahLocalDataSource
import com.devm7mdibrahim.mihrabi.model.local.surah.SurahLocalDataSource
import com.devm7mdibrahim.mihrabi.model.network.places.PlacesDataSource
import com.devm7mdibrahim.mihrabi.model.network.remote.RemoteDataSource
import com.devm7mdibrahim.mihrabi.model.shared_preference.SharedPreferenceDataSource
import com.devm7mdibrahim.mihrabi.ui.azkar.repo.AzkarRepository
import com.devm7mdibrahim.mihrabi.ui.azkar.repo.AzkarRepositoryImpl
import com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.repo.FadlElsalahRepository
import com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.repo.FadlElsalahRepositoryImpl
import com.devm7mdibrahim.mihrabi.ui.fiqh.repo.FiqhRepository
import com.devm7mdibrahim.mihrabi.ui.fiqh.repo.FiqhRepositoryImpl
import com.devm7mdibrahim.mihrabi.ui.main.repo.MainRepository
import com.devm7mdibrahim.mihrabi.ui.main.repo.MainRepositoryImpl
import com.devm7mdibrahim.mihrabi.ui.misbaha.repo.MisbahaRepository
import com.devm7mdibrahim.mihrabi.ui.misbaha.repo.MisbahaRepositoryImpl
import com.devm7mdibrahim.mihrabi.ui.mosques.repo.MosquesRepository
import com.devm7mdibrahim.mihrabi.ui.mosques.repo.MosquesRepositoryImpl
import com.devm7mdibrahim.mihrabi.ui.prayer_times.repo.PrayerTimesRepository
import com.devm7mdibrahim.mihrabi.ui.prayer_times.repo.PrayerTimesRepositoryImpl
import com.devm7mdibrahim.mihrabi.ui.qadaa.repo.QadaaRepository
import com.devm7mdibrahim.mihrabi.ui.qadaa.repo.QadaaRepositoryImpl
import com.devm7mdibrahim.mihrabi.ui.qibla.repo.QiblaRepository
import com.devm7mdibrahim.mihrabi.ui.qibla.repo.QiblaRepositoryImpl
import com.devm7mdibrahim.mihrabi.ui.quran.quran.repo.QuranRepository
import com.devm7mdibrahim.mihrabi.ui.quran.quran.repo.QuranRepositoryImpl
import com.devm7mdibrahim.mihrabi.ui.quran.surahList.repo.SurahListRepository
import com.devm7mdibrahim.mihrabi.ui.quran.surahList.repo.SurahListRepositoryImpl
import com.devm7mdibrahim.mihrabi.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideMainRepository(sharedPreferenceDataSource: SharedPreferenceDataSource): MainRepository {
        return MainRepositoryImpl(sharedPreferenceDataSource)
    }

    @Provides
    fun provideAzkarRepository(remoteDataSource: RemoteDataSource, azkarLocalDataSource: AzkarLocalDataSource, networkHelper: NetworkHelper): AzkarRepository {
        return AzkarRepositoryImpl(remoteDataSource, azkarLocalDataSource, networkHelper)
    }

    @Provides
    fun provideFiqhRepository(remoteDataSource: RemoteDataSource, fiqhLocalDataSource: FiqhLocalDataSource, networkHelper: NetworkHelper): FiqhRepository {
        return FiqhRepositoryImpl(remoteDataSource, fiqhLocalDataSource, networkHelper)
    }

    @Provides
    fun provideMisbahaRepository(sharedPreferenceDataSource: SharedPreferenceDataSource): MisbahaRepository {
        return MisbahaRepositoryImpl(sharedPreferenceDataSource)
    }

    @Provides
    fun provideMosquesRepository(sharedPreferenceDataSource: SharedPreferenceDataSource, placesDataSource: PlacesDataSource, networkHelper: NetworkHelper): MosquesRepository {
        return MosquesRepositoryImpl(sharedPreferenceDataSource, placesDataSource, networkHelper)
    }

    @Provides
    fun providePrayerTimesRepository(sharedPreferenceDataSource: SharedPreferenceDataSource): PrayerTimesRepository {
        return PrayerTimesRepositoryImpl(sharedPreferenceDataSource)
    }

    @Provides
    fun provideQadaaRepository(sharedPreferenceDataSource: SharedPreferenceDataSource): QadaaRepository {
        return QadaaRepositoryImpl(sharedPreferenceDataSource)
    }

    @Provides
    fun provideQiblaRepository(sharedPreferenceDataSource: SharedPreferenceDataSource): QiblaRepository {
        return QiblaRepositoryImpl(sharedPreferenceDataSource)
    }

    @Provides
    fun provideQuranRepository(ayahLocalDataSource: AyahLocalDataSource, remoteDataSource: RemoteDataSource, networkHelper: NetworkHelper): QuranRepository {
        return QuranRepositoryImpl(ayahLocalDataSource, remoteDataSource, networkHelper)
    }

    @Provides
    fun provideSurahListRepository(surahLocalDataSource: SurahLocalDataSource, remoteDataSource: RemoteDataSource, networkHelper: NetworkHelper): SurahListRepository {
        return SurahListRepositoryImpl(surahLocalDataSource, remoteDataSource, networkHelper)
    }

    @Provides
    fun provideFadlElsalahRepository(remoteDataSource: RemoteDataSource, fadlElsalahLocalDataSource: FadlElsalahLocalDataSource, networkHelper: NetworkHelper): FadlElsalahRepository {
        return FadlElsalahRepositoryImpl(remoteDataSource, fadlElsalahLocalDataSource, networkHelper)
    }
}