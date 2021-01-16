package com.devm7mdibrahim.mihrabi.ui.azkar.repo

import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar
import com.devm7mdibrahim.mihrabi.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class FakeAzkarRepositoryImpl :
    AzkarRepository {
    val connected = true

    override suspend fun fetchAzkar(type: Int, list: List<Azkar>): Flow<DataState<List<Azkar>>> = flow {
        if (list.isNullOrEmpty()) {
            if (connected) {
                try {
                    val list2 = listOf(Azkar(1, "sdfsdf", 1), Azkar(2, "asds", 1))
                    emit(DataState.Success(list2))
                } catch (e: Exception) {
                    emit(DataState.Error("unknown error"))
                }
            } else {
                emit(DataState.Error("network error"))
            }

        } else {
            emit(DataState.Success(list))
        }
    }
}