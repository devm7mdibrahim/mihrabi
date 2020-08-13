package com.devm7mdibrahim.mihrabi.model.local.fadl_salah

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.Fadl

@Dao
interface FadlElsalahDao {

    @Query("SELECT * FROM fadl WHERE type LIKE :type")
    suspend fun getFadlElsalahData(type: Int): List<Fadl>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFadlElsalahData(fadlElsalahList: List<Fadl>)
}