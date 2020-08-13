package com.devm7mdibrahim.mihrabi.model.local.quran

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ayah")
data class Ayah(
        @PrimaryKey
        var ayahId: Int = 0,
        var ayahIdInSurah: Int = 0,
        var surahId: Int = 0,
        var pageNumber: Int = 0,
        var juz: Int = 0,
        var text: String
)