package com.devm7mdibrahim.mihrabi.ui.qibla.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.devm7mdibrahim.mihrabi.ui.qibla.repo.QiblaRepository

class QiblaViewModel @ViewModelInject constructor(private val qiblaRepository: QiblaRepository): ViewModel() {
    fun getUserLatitude(): Double = qiblaRepository.getUserLatitude()
    fun getUserLongitude(): Double = qiblaRepository.getUserLongitude()
    fun getUserAltitude(): Double = qiblaRepository.getUserAltitude()
    fun getKaabaLatitude(): Double = qiblaRepository.getKaabaLatitude()
    fun getKaabaLongitude(): Double = qiblaRepository.getKaabaLongitude()
    fun getKaabaAltitude(): Double = qiblaRepository.getKaabaAltitude()
}