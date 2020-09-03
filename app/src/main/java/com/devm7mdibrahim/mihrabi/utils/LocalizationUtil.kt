package com.devm7mdibrahim.mihrabi.utils

import android.content.Context
import android.os.Build
import java.util.*

object LocalizationUtil {

    fun applyArabicLanguage(context: Context): Context {
        val locale = Locale("ar")
        val configuration = context.resources.configuration
        val displayMetrics = context.resources.displayMetrics

        Locale.setDefault(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale)
            context.createConfigurationContext(configuration)
        } else {
            configuration.locale = locale
            context.resources.updateConfiguration(configuration, displayMetrics)
            context
        }
    }
}