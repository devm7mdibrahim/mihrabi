package com.devm7mdibrahim.mihrabi.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devm7mdibrahim.mihrabi.model.local.quran.Ayah
import com.devm7mdibrahim.mihrabi.model.local.quran.AyahDAO
import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar
import com.devm7mdibrahim.mihrabi.model.local.azkar.AzkarDao
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.Fadl
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.FadlElsalahDao
import com.devm7mdibrahim.mihrabi.model.local.fiqh.Fiqh
import com.devm7mdibrahim.mihrabi.model.local.fiqh.FiqhDao
import com.devm7mdibrahim.mihrabi.model.local.surah.Surah
import com.devm7mdibrahim.mihrabi.model.local.surah.SurahDao

@Database(entities = [Ayah::class, Surah::class, Fiqh::class, Fadl::class, Azkar::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ayahDAO(): AyahDAO
    abstract fun surahDao(): SurahDao
    abstract fun fiqhDao(): FiqhDao
    abstract fun fadlDao(): FadlElsalahDao
    abstract fun azkarDao(): AzkarDao
}