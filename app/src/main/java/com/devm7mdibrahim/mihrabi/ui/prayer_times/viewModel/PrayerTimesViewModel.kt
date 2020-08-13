package com.devm7mdibrahim.mihrabi.ui.prayer_times.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.devm7mdibrahim.mihrabi.ui.prayer_times.repo.PrayerTimesRepository

class PrayerTimesViewModel @ViewModelInject constructor(private val prayerTimesRepository: PrayerTimesRepository) :
    ViewModel() {
    fun getUserLatitude(): Double = prayerTimesRepository.getUserLatitude()
    fun getUserLongitude(): Double = prayerTimesRepository.getUserLongitude()
    fun getUserCountry(): String = prayerTimesRepository.getUserCountry()
}