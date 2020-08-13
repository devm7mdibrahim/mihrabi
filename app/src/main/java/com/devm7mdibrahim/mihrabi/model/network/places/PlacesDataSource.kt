package com.devm7mdibrahim.mihrabi.model.network.places

import com.devm7mdibrahim.mihrabi.model.network.response.nearby_mosques.MosquesResponse
import retrofit2.Response

interface PlacesDataSource {
    suspend fun getNearbyMosques(location: String): Response<MosquesResponse>
}