package com.devm7mdibrahim.mihrabi.model.local.surah

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SurahDao {

    @Query("SELECT * FROM surah")
    suspend fun getAllSurah(): List<Surah>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSurah(sourahList: List<Surah>)
}