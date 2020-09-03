package com.devm7mdibrahim.mihrabi.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import java.util.regex.Pattern

class SpannableHelper {
    companion object {
        fun getSpannable(text: String): Spannable? {
            val spannable: Spannable = SpannableString(text)
            val regex = "للَّه"
            val p = Pattern.compile(regex)
            val m = p.matcher(text)
            var start: Int
            var end: Int

            while (m.find()) {
                start = m.start()
                while (text[start] != ' ' && start != 0) {
                    start--
                }
                end = m.end()
                while (text[end] != ' ') {
                    end++
                }
                spannable.setSpan(ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            return spannable
        }
    }
}