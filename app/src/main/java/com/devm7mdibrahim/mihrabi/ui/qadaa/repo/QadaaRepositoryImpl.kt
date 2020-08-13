package com.devm7mdibrahim.mihrabi.ui.qadaa.repo

import com.devm7mdibrahim.mihrabi.model.shared_preference.SharedPreferenceDataSource
import com.devm7mdibrahim.mihrabi.ui.qadaa.repo.QadaaRepository
import javax.inject.Inject

class QadaaRepositoryImpl @Inject constructor(private val sharedPreferenceDataSource: SharedPreferenceDataSource):
    QadaaRepository {
    override fun getQadaaData(type: Int): Int = sharedPreferenceDataSource.getQadaaData(type)
    override fun setQadaaData(type: Int, number: Int) = sharedPreferenceDataSource.setQadaaData(type, number)
}