package com.devm7mdibrahim.mihrabi.ui.prayer_times.repo

interface PrayerTimesRepository {
    fun getUserLatitude(): Double
    fun getUserLongitude(): Double
    fun getUserCountry(): String
}