package com.devm7mdibrahim.mihrabi.ui.qibla.repo

import com.devm7mdibrahim.mihrabi.model.shared_preference.SharedPreferenceDataSource
import javax.inject.Inject

class QiblaRepositoryImpl @Inject constructor(private val sharedPreferenceDataSource: SharedPreferenceDataSource):
    QiblaRepository {
    override fun getUserLatitude(): Double = sharedPreferenceDataSource.getUserLatitude().toDouble()
    override fun getUserLongitude(): Double = sharedPreferenceDataSource.getUserLongitude().toDouble()
    override fun getUserAltitude(): Double = sharedPreferenceDataSource.getUserAltitude().toDouble()
    override fun getKaabaLatitude(): Double = sharedPreferenceDataSource.getKaabaLatitude().toDouble()
    override fun getKaabaLongitude(): Double = sharedPreferenceDataSource.getKaabaLongitude().toDouble()
    override fun getKaabaAltitude(): Double = sharedPreferenceDataSource.getKaabaAltitude().toDouble()
}
