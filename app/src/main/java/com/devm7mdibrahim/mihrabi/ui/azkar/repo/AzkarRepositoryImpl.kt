package com.devm7mdibrahim.mihrabi.ui.azkar.repo

import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar
import com.devm7mdibrahim.mihrabi.model.local.azkar.AzkarLocalDataSource
import com.devm7mdibrahim.mihrabi.model.network.remote.RemoteDataSource
import com.devm7mdibrahim.mihrabi.ui.azkar.mapper.AzkarEntityMapper
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.devm7mdibrahim.mihrabi.utils.NetworkHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AzkarRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val azkarLocalDataSource: AzkarLocalDataSource,
    private val networkHelper: NetworkHelper,
    private val azkarEntityMapper: AzkarEntityMapper
) : AzkarRepository {

    private val _azkar = MutableStateFlow<DataState<List<Azkar>>>(DataState.Idle)
    val azkar: StateFlow<DataState<List<Azkar>>>
        get() = _azkar

    override suspend fun fetchAzkar(type: Int, list: List<Azkar>) =
        flow {

            _azkar.value = DataState.Loading

            azkarLocalDataSource.getAzkar(type).let {
                if (it.isEmpty()){
                    if (networkHelper.isNetworkConnected()) {
                        try {
                            val azkarList = remoteDataSource.getAzkar(type)
                            azkarList.body()?.let {
                                val transformToLocal = azkarEntityMapper.transformToLocal(it.content)
                                azkarLocalDataSource.insertAzkar(transformToLocal)
                                emit(DataState.Success(transformToLocal))
                            }
                        } catch (e: Exception) {
                            emit(DataState.Error("برجاء التأكد من الاتصال بالانترنت والمحاولة مرة أخرى :(("))
                        }
                    } else {
                        emit(DataState.Error("لا يوجد اتصال بالانترنت :(("))
                    }
                }else{
                    val cachedAzkar = azkarLocalDataSource.getAzkar(type)
                    emit(DataState.Success(cachedAzkar))
                }
            }
        }
}