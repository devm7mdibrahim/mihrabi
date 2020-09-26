package com.devm7mdibrahim.mihrabi.model.local.quran

import javax.inject.Inject

class AyahLocalDataSourceImpl @Inject constructor(private val ayahDAO: AyahDAO) :
    AyahLocalDataSource {
    override suspend fun insertAllAyat(ayatList: List<Ayah>) = ayahDAO.insertAllAyat(ayatList)
    override suspend fun getAyahsByPage(page: Int): List<Ayah> = ayahDAO.getAyahsByPage(page)
    override suspend fun getAyatCount(): Int = ayahDAO.getAyatCount()
}