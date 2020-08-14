package com.devm7mdibrahim.mihrabi.ui.quran.quran.adapter

import com.devm7mdibrahim.mihrabi.model.local.quran.Ayah
import com.devm7mdibrahim.mihrabi.utils.NumberHelper
import java.text.MessageFormat

data class Page(var ayat: List<Ayah>, var pageNumber: Int = 0, var juz: Int = 0) {

    fun getText(a: (x: Int) -> String): String {
        val builder = StringBuilder()
        var isFirst = true

        ayat.let {
            for (ayahItem in it) {
                var ayah = ayahItem.text
                if (ayahItem.ayahIdInSurah == 1) {
                    val tempSuraName = a(ayahItem.surahId)
                    if (isFirst) {
                        builder.append(tempSuraName + "\n")
                    } else {
                        builder.append("\n" + tempSuraName + "\n")
                    }

                    if (ayahItem.surahId != 1 && ayahItem.surahId != 9) {
                        var pos = ayah.indexOf("ٱلرَّحِيم")
                        pos += "ٱلرَّحِيم".length
                        builder.append(ayah.substring(0, pos + 1))
                        builder.append("\n")
                        ayah = ayah.substring(pos + 1)
                    }
                }
                isFirst = false
                builder.append(MessageFormat.format("{0}   ﴿{1}﴾  ", ayah, NumberHelper.getArabicNumber(ayahItem.ayahIdInSurah)))
            }
        }

        return builder.toString()
    }
}