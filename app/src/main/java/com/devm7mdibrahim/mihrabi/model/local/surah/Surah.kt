package com.devm7mdibrahim.mihrabi.model.local.surah

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surah")
data class Surah(
        @PrimaryKey val number: Int,
        val surahName: String,
        val revelationType: String,
        val numOfAyahs: Int,
        val page: Int
)