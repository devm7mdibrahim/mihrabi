package com.devm7mdibrahim.mihrabi.model.network.remote

import com.devm7mdibrahim.mihrabi.model.network.response.azkar.AzkarResponse
import com.devm7mdibrahim.mihrabi.model.network.response.fadl_salah.FadlElsalahResponse
import com.devm7mdibrahim.mihrabi.model.network.response.fiqh.FiqhResponse
import com.devm7mdibrahim.mihrabi.model.network.response.quran.QuranResponse
import com.devm7mdibrahim.mihrabi.model.network.response.surah.SurahResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getQuranData(): Response<QuranResponse>
    suspend fun getQuranSurah(): Response<SurahResponse>
    suspend fun getAzkar(azkar_type: Int): Response<AzkarResponse>
    suspend fun getFiqh(fiqh_type : Int): Response<FiqhResponse>
    suspend fun getFadlElsalah(fadl_type: Int): Response<FadlElsalahResponse>
}