package com.devm7mdibrahim.mihrabi.model.local.azkar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar

@Dao
interface AzkarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAzkar(azkar: List<Azkar>)

    @Query("SELECT * FROM azkar WHERE type LIKE :type")
    suspend fun getAzkar(type: Int): List<Azkar>
}