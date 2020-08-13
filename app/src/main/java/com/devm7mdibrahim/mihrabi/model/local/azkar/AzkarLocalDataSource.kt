package com.devm7mdibrahim.mihrabi.model.local.azkar

interface AzkarLocalDataSource {
    suspend fun insertAzkar(azkar: List<Azkar>)
    suspend fun getAzkar(type: Int): List<Azkar>
}