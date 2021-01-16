package com.devm7mdibrahim.mihrabi.ui.app

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.utils.Constants.PRAYER_CHANNEL_ID
import com.devm7mdibrahim.mihrabi.utils.LocalizationUtil
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {

    private lateinit var notificationManager: NotificationManager

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(LocalizationUtil.applyArabicLanguage(context))

        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createPrayerTimesNotificationChannel()
            createAzkarNotificationChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createPrayerTimesNotificationChannel() {


        val mChannel = NotificationChannel(
            PRAYER_CHANNEL_ID,
            "prayer_times_channel",
            NotificationManager.IMPORTANCE_HIGH
        )

        mChannel.run {
            description = "prayer times notification channel"
            enableLights(true)
            lightColor = getColor(R.color.colorPrimaryDark)
            enableVibration(true)
            vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            setShowBadge(false)
        }

        notificationManager.createNotificationChannel(mChannel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createAzkarNotificationChannel() {

        val mChannel = NotificationChannel(
            PRAYER_CHANNEL_ID,
            "azkar_alarm_channel",
            NotificationManager.IMPORTANCE_HIGH
        )

        mChannel.run {
            description = "azkar notification channel"
            enableLights(true)
            lightColor = getColor(R.color.colorPrimaryDark)
            enableVibration(true)
            vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            setShowBadge(false)
        }

        notificationManager.createNotificationChannel(mChannel)
    }
}