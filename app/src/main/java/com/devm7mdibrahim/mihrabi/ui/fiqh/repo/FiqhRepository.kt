package com.devm7mdibrahim.mihrabi.ui.fiqh.repo

import com.devm7mdibrahim.mihrabi.model.local.fiqh.Fiqh
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.flow.Flow

interface FiqhRepository {
    suspend fun fetchFiqhData(type: Int) : Flow<DataState<List<Fiqh>>>
}