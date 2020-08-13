package com.devm7mdibrahim.mihrabi.di

import android.content.Context
import android.content.SharedPreferences
import com.devm7mdibrahim.mihrabi.model.shared_preference.SharedPreferenceDataSource
import com.devm7mdibrahim.mihrabi.model.shared_preference.SharedPreferenceDataSourceImpl
import com.devm7mdibrahim.mihrabi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SharedPreferenceModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }


    @Singleton
    @Provides
    fun provideSharedPreferenceDataSource(sharedPreferences: SharedPreferences): SharedPreferenceDataSource {
        return SharedPreferenceDataSourceImpl(sharedPreferences)
    }
}