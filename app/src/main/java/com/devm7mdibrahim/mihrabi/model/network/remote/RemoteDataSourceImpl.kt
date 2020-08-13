package com.devm7mdibrahim.mihrabi.model.network.remote

import com.devm7mdibrahim.mihrabi.model.network.response.azkar.AzkarResponse
import com.devm7mdibrahim.mihrabi.model.network.response.fadl_salah.FadlElsalahResponse
import com.devm7mdibrahim.mihrabi.model.network.response.fiqh.FiqhResponse
import com.devm7mdibrahim.mihrabi.model.network.response.quran.QuranResponse
import com.devm7mdibrahim.mihrabi.model.network.response.surah.SurahResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {
    override suspend fun getQuranData(): Response<QuranResponse> = apiService.getQuranData()
    override suspend fun getQuranSurah(): Response<SurahResponse> = apiService.getQuranSourah()
    override suspend fun getAzkar(azkar_type: Int): Response<AzkarResponse> = apiService.getAzkar(azkar_type)
    override suspend fun getFiqh(fiqh_type: Int): Response<FiqhResponse> = apiService.getFiqh(fiqh_type)
    override suspend fun getFadlElsalah(fadl_type: Int): Response<FadlElsalahResponse> = apiService.getFadlElsalah(fadl_type)
}