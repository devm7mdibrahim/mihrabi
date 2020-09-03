package com.devm7mdibrahim.mihrabi.ui.app

import android.app.Application
import android.content.Context
import com.devm7mdibrahim.mihrabi.utils.LocalizationUtil
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        createNotificationChannel1()
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(LocalizationUtil.applyArabicLanguage(context))

    }

    private fun createNotificationChannel1() {

    }
}