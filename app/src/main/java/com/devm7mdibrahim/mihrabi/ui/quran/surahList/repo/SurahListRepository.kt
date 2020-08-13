package com.devm7mdibrahim.mihrabi.ui.quran.surahList.repo

import com.devm7mdibrahim.mihrabi.model.local.surah.Surah
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.flow.Flow

interface SurahListRepository {
    suspend fun getSurahList(): Flow<DataState<List<Surah>>>
}