package com.devm7mdibrahim.mihrabi.model.local.surah

import javax.inject.Inject

class SurahLocalDataSourceImpl @Inject constructor(private val surahDao: SurahDao) :
    SurahLocalDataSource {
    override suspend fun getAllSurah(): List<Surah> = surahDao.getAllSurah()
    override suspend fun insertAllSurah(sourahList: List<Surah>) = surahDao.insertAllSurah(sourahList)
}