package com.devm7mdibrahim.mihrabi.ui.mosques.repo

import com.devm7mdibrahim.mihrabi.model.network.response.nearby_mosques.Result
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MosquesRepository {
    fun getLocation(): String
    suspend fun getNearbyMosques(location: String): Flow<DataState<List<Result>>>
}