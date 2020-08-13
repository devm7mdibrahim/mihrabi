package com.devm7mdibrahim.mihrabi.ui.prayer_times.repo

import com.devm7mdibrahim.mihrabi.model.shared_preference.SharedPreferenceDataSource
import javax.inject.Inject

class PrayerTimesRepositoryImpl @Inject constructor(private val sharedPreferenceDataSource: SharedPreferenceDataSource):
    PrayerTimesRepository {
    override fun getUserLatitude(): Double = sharedPreferenceDataSource.getUserLatitude().toDouble()
    override fun getUserLongitude(): Double = sharedPreferenceDataSource.getUserLongitude().toDouble()
    override fun getUserCountry(): String = sharedPreferenceDataSource.getUserCountry()
}