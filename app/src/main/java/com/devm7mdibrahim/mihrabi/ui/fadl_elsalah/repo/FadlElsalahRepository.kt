package com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.repo

import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.Fadl
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.flow.Flow

interface FadlElsalahRepository {
    suspend fun fetchFadlElsalahData(type: Int) : Flow<DataState<List<Fadl>>>
}