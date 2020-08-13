package com.devm7mdibrahim.mihrabi.model.local.fiqh

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devm7mdibrahim.mihrabi.model.local.fiqh.Fiqh

@Dao
interface FiqhDao {

    @Query("SELECT * FROM fiqh WHERE type LIKE :type")
    suspend fun getFiqhData(type: Int): List<Fiqh>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFiqhData(fiqhElsalahList: List<Fiqh>)
}