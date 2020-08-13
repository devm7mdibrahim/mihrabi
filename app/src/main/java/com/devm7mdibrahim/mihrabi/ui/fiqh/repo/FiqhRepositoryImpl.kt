package com.devm7mdibrahim.mihrabi.ui.fiqh.repo

import com.devm7mdibrahim.mihrabi.model.local.fiqh.Fiqh
import com.devm7mdibrahim.mihrabi.model.local.fiqh.FiqhLocalDataSource
import com.devm7mdibrahim.mihrabi.model.network.remote.RemoteDataSource
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.devm7mdibrahim.mihrabi.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FiqhRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val fiqhDataSource: FiqhLocalDataSource,
    private val networkHelper: NetworkHelper
) : FiqhRepository {

    override suspend fun fetchFiqhData(type: Int): Flow<DataState<List<Fiqh>>> = flow {
        emit(DataState.Loading)

        if (fiqhDataSource.getFiqhData(type).isEmpty()) {
            if (networkHelper.isNetworkConnected()) {
                try {
                    val fiqhList = remoteDataSource.getFiqh(type)
                    val fiqhListToInsertToDB = mutableListOf<Fiqh>()
                    for (fiqh in fiqhList.body()!!.content) {
                        fiqhListToInsertToDB.add(
                            Fiqh(
                                id = fiqh.id,
                                question = fiqh.question,
                                answer = fiqh.answer,
                                type = fiqh.type
                            )
                        )
                    }
                    fiqhDataSource.insertFiqhData(fiqhListToInsertToDB)

                    val cachedFiqh = fiqhDataSource.getFiqhData(type)
                    emit(DataState.Success(cachedFiqh))
                } catch (exception: Exception) {
                    emit(DataState.Error("برجاء التأكد من الاتصال بالانترنت والمحاولة مرة أخرى :(("))
                }
            } else {
                emit(DataState.Error("لا يوجد اتصال بالانترنت :(("))
            }
        } else {
            val cachedFiqh = fiqhDataSource.getFiqhData(type)
            emit(DataState.Success(cachedFiqh))
        }
    }
}