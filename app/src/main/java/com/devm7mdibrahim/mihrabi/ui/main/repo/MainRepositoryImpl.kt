package com.devm7mdibrahim.mihrabi.ui.main.repo

import com.devm7mdibrahim.mihrabi.model.shared_preference.SharedPreferenceDataSource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val sharedPreferenceDataSource: SharedPreferenceDataSource):
    MainRepository {
    override fun updateUserLatitude(latitude: String) = sharedPreferenceDataSource.setUserLatitude(latitude)
    override fun updateUserLongitude(longitude: String) = sharedPreferenceDataSource.setUserLongitude(longitude)
    override fun updateUserAltitude(altitude: String) = sharedPreferenceDataSource.setUserAltitude(altitude)
    override fun updateUserCity(city: String) = sharedPreferenceDataSource.setUserCountry(city)
}