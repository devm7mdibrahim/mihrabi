package com.devm7mdibrahim.mihrabi.ui.quran.surahList.repo

import android.util.Log
import com.devm7mdibrahim.mihrabi.model.local.surah.Surah
import com.devm7mdibrahim.mihrabi.model.local.surah.SurahLocalDataSource
import com.devm7mdibrahim.mihrabi.model.network.remote.RemoteDataSource
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.devm7mdibrahim.mihrabi.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SurahListRepositoryImpl constructor(private val surahLocalDataSource: SurahLocalDataSource, private val remoteDataSource: RemoteDataSource, private val networkHelper: NetworkHelper) :
    SurahListRepository {
    override suspend fun getSurahList(): Flow<DataState<List<Surah>>> = flow {
        emit(DataState.Loading)

        try {
            if (surahLocalDataSource.getAllSurah().isEmpty()) {
                if (networkHelper.isNetworkConnected()) {
                    val quranSurahListResponse = remoteDataSource.getQuranSurah()
                    if (quranSurahListResponse.isSuccessful && quranSurahListResponse.body() != null) {
                        val surahListToInsertToDB: MutableList<Surah> = mutableListOf()
                        for (surah in quranSurahListResponse.body()!!.data) {
                            surahListToInsertToDB.add(Surah(surah.number, surah.name, surah.revelationType, surah.numberOfAyahs, surah.page))
                        }
                        surahLocalDataSource.insertAllSurah(surahListToInsertToDB)
                        emit(DataState.Success(surahListToInsertToDB))
                    } else {
                        emit(DataState.Error("حدث خطأ ما، برجاء المحاولة في وقت لاحق :(("))
                    }
                } else {
                    emit(DataState.Error("لا يوجد اتصال بالانترنت :(("))
                }

            } else {
                emit(DataState.Success(surahLocalDataSource.getAllSurah()))
            }
        } catch (e: Exception) {
            Log.d(TAG, "getSurahList: "+ e.message)
            emit(DataState.Error("حدث خطأ ما، برجاء المحاولة في وقت لاحق :(("))
        }

    }
}