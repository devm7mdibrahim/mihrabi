package com.devm7mdibrahim.mihrabi.reminder

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.ui.main.activity.MainActivity
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG
import java.util.*

class NotificationWorker(val context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    override fun doWork(): Result {

        val prayingNum = inputData.getInt(AppWorker.PRAYER_NUM, 0)

        Log.d(TAG, "doWork: ")

        displayPrayerNotification(prayingNum)

        return Result.success()
    }

    private fun displayPrayerNotification(prayingNum: Int) {
        var prayingName = ""

        when (prayingNum) {
            0 -> prayingName = context.getString(R.string.fajr_prayer)
            1 -> prayingName = context.getString(R.string.sunrize_prayer)
            2 -> {
                val calendar = Calendar.getInstance()
                val day = calendar[Calendar.DAY_OF_WEEK]
                prayingName =
                    if (day == Calendar.FRIDAY) context.getString(R.string.jummah_prayer) else context.getString(
                        R.string.zuhr_prayer
                    )
            }

            3 -> prayingName = context.getString(R.string.asr_prayer)
            4 -> prayingName = context.getString(R.string.maghreb_prayer)
            5 -> prayingName = context.getString(R.string.ishaa_prayer)
        }

        Log.d(TAG, "displayPrayerNotification: $prayingName")
        val intent = PendingIntent.getActivity(
            context, 0, Intent(context, MainActivity::class.java), 0
        )
        val builder =
            NotificationCompat.Builder(context, Constants.PRAYER_CHANNEL_ID)
                .setSmallIcon(R.drawable.app_icon)
                .setContentText(prayingName)
                .setContentTitle("${context.getString(R.string.remember)} $prayingName")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setLights(-0xff0100, 1000, 1000)
                .setAutoCancel(true)
                .setContentIntent(intent)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(prayingNum, builder.build())
    }
}