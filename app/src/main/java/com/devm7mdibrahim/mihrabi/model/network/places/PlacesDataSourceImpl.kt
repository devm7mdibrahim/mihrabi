package com.devm7mdibrahim.mihrabi.model.network.places

import com.devm7mdibrahim.mihrabi.model.network.response.nearby_mosques.MosquesResponse
import retrofit2.Response
import javax.inject.Inject

class PlacesDataSourceImpl @Inject constructor(private val placesApiService: PlacesApiService) :
    PlacesDataSource {
    override suspend fun getNearbyMosques(location: String): Response<MosquesResponse> = placesApiService.getNearbyMosques(location)
}