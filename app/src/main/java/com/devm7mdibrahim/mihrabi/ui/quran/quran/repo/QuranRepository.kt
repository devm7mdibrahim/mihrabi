package com.devm7mdibrahim.mihrabi.ui.quran.quran.repo

import com.devm7mdibrahim.mihrabi.ui.quran.quran.adapter.Page
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    suspend fun getQuranPagesList(): Flow<DataState<List<Page>>>
}