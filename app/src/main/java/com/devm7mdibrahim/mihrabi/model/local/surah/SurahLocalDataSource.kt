package com.devm7mdibrahim.mihrabi.model.local.surah

import com.devm7mdibrahim.mihrabi.model.local.surah.Surah

interface SurahLocalDataSource {
    suspend fun getAllSurah(): List<Surah>
    suspend fun insertAllSurah(sourahList: List<Surah>)
}