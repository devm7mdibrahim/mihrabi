package com.devm7mdibrahim.mihrabi.ui.mosques.repo

import com.devm7mdibrahim.mihrabi.model.network.places.PlacesDataSource
import com.devm7mdibrahim.mihrabi.model.network.response.nearby_mosques.Result
import com.devm7mdibrahim.mihrabi.model.shared_preference.SharedPreferenceDataSource
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.devm7mdibrahim.mihrabi.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MosquesRepositoryImpl @Inject constructor(private val sharedPreferenceDataSource: SharedPreferenceDataSource,
                                                private val placesDataSource: PlacesDataSource,
                                                private val networkHelper: NetworkHelper
) : MosquesRepository {

    override fun getLocation(): String {
        val userLatitude = sharedPreferenceDataSource.getUserLatitude()
        val userLongitude = sharedPreferenceDataSource.getUserLongitude()

        return "$userLatitude, $userLongitude"
    }

    override suspend fun getNearbyMosques(location: String): Flow<DataState<List<Result>>> = flow {
        emit(DataState.Loading)

        if (networkHelper.isNetworkConnected()) {
            try {
                val response = placesDataSource.getNearbyMosques(location)

                if (response.isSuccessful && response.body() != null && !response.body()!!.results.isNullOrEmpty()) {
                    emit(DataState.Success(response.body()!!.results))
                } else {
                    emit(DataState.Error("حدث خطأ ما، برجاء المحاولة في وقت لاحق :(("))
                }
            } catch (e: Exception) {
                emit(DataState.Error("حدث خطأ ما، برجاء المحاولة في وقت لاحق :(("))
            }
        } else {
            emit(DataState.Error("لا يوجد اتصال بالانترنت :(("))
        }
    }
}