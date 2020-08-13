package com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.repo

import android.util.Log
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.Fadl
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.FadlElsalahLocalDataSource
import com.devm7mdibrahim.mihrabi.model.network.remote.RemoteDataSource
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.devm7mdibrahim.mihrabi.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FadlElsalahRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val fadlElsalahLocalDataSource: FadlElsalahLocalDataSource,
    private val networkHelper: NetworkHelper
) : FadlElsalahRepository {

    override suspend fun fetchFadlElsalahData(type: Int): Flow<DataState<List<Fadl>>> = flow {
        emit(DataState.Loading)

        if (fadlElsalahLocalDataSource.getFadlElsalahData(type).isEmpty()) {
            if (networkHelper.isNetworkConnected()) {
                try {
                    val fadlList = remoteDataSource.getFadlElsalah(type)
                    val fadlListToInsertToDB = mutableListOf<Fadl>()
                    for (fadl in fadlList.body()!!.content) {
                        fadlListToInsertToDB.add(
                            Fadl(
                                id = fadl.id,
                                text = fadl.text,
                                type = fadl.type
                            )
                        )
                    }
                    fadlElsalahLocalDataSource.insertFadlElsalahData(fadlListToInsertToDB)

                    val cachedFiqh = fadlElsalahLocalDataSource.getFadlElsalahData(type)
                    Log.d(TAG, "fetchFadlElsalahData: ${cachedFiqh.size}")
                    emit(DataState.Success(cachedFiqh))
                } catch (exception: Exception) {
                    Log.d(TAG, "fetchFadlElsalahData: ${exception.message}")
                    emit(DataState.Error("برجاء التأكد من الاتصال بالانترنت والمحاولة مرة أخرى :(("))
                }
            } else {
                Log.d(TAG, "fetchFadlElsalahData2: unknown error")
                emit(DataState.Error("لا يوجد اتصال بالانترنت :(("))
            }
        } else {
            val cachedFiqh = fadlElsalahLocalDataSource.getFadlElsalahData(type)
            emit(DataState.Success(cachedFiqh))
        }
    }
}