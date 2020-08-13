package com.devm7mdibrahim.mihrabi.model.local.azkar

import javax.inject.Inject

class AzkarLocalDataSourceImpl @Inject constructor(private val azkarDao: AzkarDao):
    AzkarLocalDataSource {
    override suspend fun insertAzkar(azkar: List<Azkar>) = azkarDao.insertAzkar(azkar)
    override suspend fun getAzkar(type: Int): List<Azkar> = azkarDao.getAzkar(type)
}