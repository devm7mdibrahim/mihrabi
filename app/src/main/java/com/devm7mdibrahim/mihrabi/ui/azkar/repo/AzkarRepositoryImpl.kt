package com.devm7mdibrahim.mihrabi.ui.azkar.repo

import android.util.Log
import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar
import com.devm7mdibrahim.mihrabi.model.local.azkar.AzkarLocalDataSource
import com.devm7mdibrahim.mihrabi.model.network.remote.RemoteDataSource
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.devm7mdibrahim.mihrabi.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AzkarRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val azkarLocalDataSource: AzkarLocalDataSource,
    private val networkHelper: NetworkHelper
) : AzkarRepository {

    override suspend fun fetchAzkar(type: Int): Flow<DataState<List<Azkar>>> = flow {
        emit(DataState.Loading)

        if (azkarLocalDataSource.getAzkar(type).isEmpty()) {
            if (networkHelper.isNetworkConnected()) {
                try {
                    val azkarList = remoteDataSource.getAzkar(type)
                    val azkarListToInsertToDB = mutableListOf<Azkar>()
                    for (zekr in azkarList.body()!!.content) {
                        azkarListToInsertToDB.add(
                            Azkar(
                                id = zekr.id,
                                text = zekr.zekr,
                                type = zekr.type
                            )
                        )
                    }
                    azkarLocalDataSource.insertAzkar(azkarListToInsertToDB)

                    val cachedAzkar = azkarLocalDataSource.getAzkar(type)
                    emit(DataState.Success(cachedAzkar))
                } catch (exception: Exception) {
                    Log.d(TAG, "fetchAzkar: " + exception.message)
                    emit(DataState.Error("برجاء التأكد من الاتصال بالانترنت والمحاولة مرة أخرى :(("))
                }
            } else {
                emit(DataState.Error("لا يوجد اتصال بالانترنت :(("))
            }
        } else {
            val cachedAzkar = azkarLocalDataSource.getAzkar(type)
            emit(DataState.Success(cachedAzkar))
        }
    }
}