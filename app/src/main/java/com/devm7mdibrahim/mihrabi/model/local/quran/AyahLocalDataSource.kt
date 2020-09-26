package com.devm7mdibrahim.mihrabi.model.local.quran

interface AyahLocalDataSource {
    suspend fun insertAllAyat(ayatList: List<Ayah>)
    suspend fun getAyahsByPage(page: Int): List<Ayah>
    suspend fun getAyatCount(): Int
}