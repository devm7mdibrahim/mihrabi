package com.devm7mdibrahim.mihrabi.model.local.fadl_salah

interface FadlElsalahLocalDataSource {
    suspend fun getFadlElsalahData(type: Int): List<Fadl>
    suspend fun insertFadlElsalahData(fadlElsalahList: List<Fadl>)
}