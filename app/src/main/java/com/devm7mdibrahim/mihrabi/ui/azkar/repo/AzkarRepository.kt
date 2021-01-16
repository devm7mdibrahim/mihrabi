package com.devm7mdibrahim.mihrabi.ui.azkar.repo

import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.flow.Flow

interface AzkarRepository {
    suspend fun fetchAzkar(type: Int, list: List<Azkar>): Flow<DataState<List<Azkar>>>
}