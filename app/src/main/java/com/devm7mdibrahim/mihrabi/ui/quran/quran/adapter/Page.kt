package com.devm7mdibrahim.mihrabi.ui.quran.quran.adapter

import com.devm7mdibrahim.mihrabi.model.local.quran.Ayah
import com.devm7mdibrahim.mihrabi.utils.NumberHelper
import java.text.MessageFormat

data class Page(var ayat: List<Ayah>, var pageNumber: Int = 0, var juz: Int = 0) {

    fun getText(a: (x: Int) -> String): String {
        val builder = StringBuilder()
        var ayah: String
        var tempSuraName: String
        var isFirst = true

        ayat.let {
            for (ayahItem in it) {
                ayah = ayahItem.text
                if (ayahItem.ayahIdInSurah == 1) {
                    tempSuraName = a(ayahItem.surahId)
                    if (isFirst) {
                        builder.append(tempSuraName + "\n")
                    } else {
                        builder.append("\n" + tempSuraName + "\n")
                    }

                    if (ayahItem.surahId != 9) {
                        builder.append(ayah.substring(0, 38))
                        builder.append("\n")
                    }
                }
                isFirst = false
                builder.append(MessageFormat.format("{0}   ﴿{1}﴾  ", ayah, NumberHelper.getArabicNumber(ayahItem.ayahIdInSurah)))
            }
        }

        return builder.toString()
    }
}