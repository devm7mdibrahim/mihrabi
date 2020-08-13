package com.devm7mdibrahim.mihrabi.ui.main.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.devm7mdibrahim.mihrabi.ui.main.repo.MainRepository

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository): ViewModel() {
    fun updateUserLatitude(latitude: String) = mainRepository.updateUserLatitude(latitude)
    fun updateUserLongitude(longitude: String) = mainRepository.updateUserLongitude(longitude)
    fun updateUserAltitude(altitude: String) = mainRepository.updateUserAltitude(altitude)
    fun updateUserCity(city: String) = mainRepository.updateUserCity(city)
}