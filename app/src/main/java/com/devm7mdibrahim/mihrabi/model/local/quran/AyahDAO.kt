package com.devm7mdibrahim.mihrabi.model.local.quran

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devm7mdibrahim.mihrabi.model.local.quran.Ayah

@Dao
interface AyahDAO {
    @Insert
    suspend fun insertAllAyat(ayatList: List<Ayah>)

    @Query("select * from ayah where pageNumber = :page order by ayahId")
    suspend fun getAyahsByPage(page: Int): List<Ayah>

    @Query("SELECT COUNT(*) FROM  ayah")
    suspend fun getAyatCount(): Int
}