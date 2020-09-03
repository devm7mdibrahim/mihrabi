package com.devm7mdibrahim.mihrabi.ui.imaniat.repo

import com.devm7mdibrahim.mihrabi.model.shared_preference.SharedPreferenceDataSource
import javax.inject.Inject

class ImaniatRepositoryImpl
@Inject constructor(private val sharedPreferenceDataSource: SharedPreferenceDataSource) : ImaniatRepository {
    override fun getImaniatFirstTime(): Boolean = sharedPreferenceDataSource.isFirstTimeInImaniat()
    override fun setImaniatFirstTime(boolean: Boolean) = sharedPreferenceDataSource.setIsFirstTimeInImaniat(boolean)
}