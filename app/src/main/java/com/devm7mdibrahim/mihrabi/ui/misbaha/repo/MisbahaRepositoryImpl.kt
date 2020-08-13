package com.devm7mdibrahim.mihrabi.ui.misbaha.repo

import com.devm7mdibrahim.mihrabi.model.shared_preference.SharedPreferenceDataSource
import javax.inject.Inject

class MisbahaRepositoryImpl @Inject constructor(private val sharedPreferenceDataSource: SharedPreferenceDataSource) :
    MisbahaRepository {
    override fun getMisbahaCount() = sharedPreferenceDataSource.getMisbahaCount()
    override fun setMisbahaCount(count: Int) = sharedPreferenceDataSource.setMisbahaCount(count)
    override fun isVibrateOn(): Boolean = sharedPreferenceDataSource.isVibrationOn()
    override fun setVibrateOn(boolean: Boolean) = sharedPreferenceDataSource.setVibrationOn(boolean)
}