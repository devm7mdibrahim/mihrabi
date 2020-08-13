package com.devm7mdibrahim.mihrabi.ui.quran.quran.repo

import com.devm7mdibrahim.mihrabi.model.local.quran.Ayah
import com.devm7mdibrahim.mihrabi.model.local.quran.AyahLocalDataSource
import com.devm7mdibrahim.mihrabi.model.network.remote.RemoteDataSource
import com.devm7mdibrahim.mihrabi.ui.quran.quran.adapter.Page
import com.devm7mdibrahim.mihrabi.ui.quran.quran.repo.QuranRepository
import com.devm7mdibrahim.mihrabi.utils.DataState
import com.devm7mdibrahim.mihrabi.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuranRepositoryImpl @Inject constructor(private val ayahLocalDataSource: AyahLocalDataSource, private val remoteDataSource: RemoteDataSource, private val networkHelper: NetworkHelper) :
    QuranRepository {
    override suspend fun getQuranPagesList(): Flow<DataState<List<Page>>> = flow {
        emit(DataState.Loading)

        if (ayahLocalDataSource.getAyatCount() == 6236) {
            emit(DataState.Success(getDataFromDatabase()))
        } else {
            try {
                if (networkHelper.isNetworkConnected()) {
                    val quranResponse = remoteDataSource.getQuranData()
                    if (quranResponse.isSuccessful && quranResponse.body() != null) {
                        val quranDataToInsertToDB = mutableListOf<Ayah>()
                        for (surah in quranResponse.body()!!.data.surahs) {
                            for (ayah in surah.ayahs) {
                                quranDataToInsertToDB.add(Ayah(ayah.number, ayah.numberInSurah, surah.number, ayah.page, ayah.juz, ayah.text))
                            }
                        }
                        ayahLocalDataSource.insertAllAyat(quranDataToInsertToDB)
                        emit(DataState.Success(getDataFromDatabase()))
                    } else {
                        emit(DataState.Error("حدث خطأ ما، برجاء المحاولة في وقت لاحق :(("))
                    }
                } else {
                    emit(DataState.Error("لا يوجد اتصال بالانترنت :(("))
                }
            } catch (e: Exception) {
                emit(DataState.Error("حدث خطأ ما، برجاء المحاولة في وقت لاحق :(("))
            }
        }
    }

    private suspend fun getDataFromDatabase(): MutableList<Page> {
        val pages: MutableList<Page> = ArrayList()
        for (i in 1..604) {
            val ayahsByPage = ayahLocalDataSource.getAyahsByPage(i)
            pages.add(Page(ayat = ayahsByPage, pageNumber = i, juz = ayahsByPage[0].juz))
        }
        return pages
    }
}