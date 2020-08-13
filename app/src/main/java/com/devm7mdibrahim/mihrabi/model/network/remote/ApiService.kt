package com.devm7mdibrahim.mihrabi.model.network.remote

import com.devm7mdibrahim.mihrabi.model.network.response.azkar.AzkarResponse
import com.devm7mdibrahim.mihrabi.model.network.response.fadl_salah.FadlElsalahResponse
import com.devm7mdibrahim.mihrabi.model.network.response.fiqh.FiqhResponse
import com.devm7mdibrahim.mihrabi.model.network.response.quran.QuranResponse
import com.devm7mdibrahim.mihrabi.model.network.response.surah.SurahResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("json/quran/quran.json")
    suspend fun getQuranData(): Response<QuranResponse>

    @GET("json/quran/surah.json")
    suspend fun getQuranSourah(): Response<SurahResponse>

    @GET("json/fiqh/{fiqh_type}.json")
    suspend fun getFiqh(@Path("fiqh_type") fiqh_type : Int): Response<FiqhResponse>

    @GET("json/azkar/{azkar_type}.json")
    suspend fun getAzkar(@Path("azkar_type") azkar_type: Int): Response<AzkarResponse>

    @GET("json/fadl_salah/{fadl_type}.json")
    suspend fun getFadlElsalah(@Path("fadl_type") fadl_type: Int): Response<FadlElsalahResponse>
}