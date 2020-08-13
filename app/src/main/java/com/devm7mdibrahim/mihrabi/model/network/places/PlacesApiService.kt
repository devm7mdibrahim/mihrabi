package com.devm7mdibrahim.mihrabi.model.network.places

import com.devm7mdibrahim.mihrabi.model.network.response.nearby_mosques.MosquesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {

    @GET("place/nearbysearch/json")
    suspend fun getNearbyMosques(@Query("location") location: String): Response<MosquesResponse>
}