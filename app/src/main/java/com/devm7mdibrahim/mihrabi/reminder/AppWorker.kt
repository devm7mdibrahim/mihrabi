package com.devm7mdibrahim.mihrabi.reminder

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.work.*
import com.azan.Azan
import com.azan.Method.Companion.EGYPT_SURVEY
import com.azan.Time
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.devm7mdibrahim.mihrabi.utils.Constants.SHARED_PREFERENCE_NAME
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG
import com.devm7mdibrahim.mihrabi.utils.Constants.USER_LAT
import com.devm7mdibrahim.mihrabi.utils.Constants.USER_LONG
import java.lang.System.currentTimeMillis
import java.util.*
import java.util.concurrent.TimeUnit

class AppWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        getPrayerTimes()
        return Result.success()
    }

    private fun getPrayerTimes() {

        //get location
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val latitude = sharedPreferences.getString(USER_LAT, "30.0444")
        val longitude = sharedPreferences.getString(USER_LONG, "31.2357")


        //get prayer times
        val mGMTOffset = GregorianCalendar().timeZone.rawOffset
        val gmtDiff = TimeUnit.HOURS.convert(
            mGMTOffset.toLong(),
            TimeUnit.MILLISECONDS
        )

        val today = SimpleDate(GregorianCalendar())
        var location: Location? = null
        if (latitude != null && longitude != null) {
            location = Location(
                latitude.toDouble(),
                longitude.toDouble(),
                gmtDiff.toDouble(),
                0
            )
        }
        val azan = Azan(location, EGYPT_SURVEY)

        val prayers = azan.getPrayerTimes(today).times

        //schedule notification
        scheduleNotification(prayers)
    }

    private fun scheduleNotification(prayers: Array<Time>) {
        for (i in prayers.indices) {

            val prayer = prayers[i]

            val data = Data.Builder()
                .putInt(PRAYER_NUM, i)
                .build()

            val currentTime = currentTimeMillis()

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = currentTimeMillis()
            calendar[Calendar.HOUR_OF_DAY] = prayer.hour
            calendar[Calendar.MINUTE] = prayer.minute
            calendar[Calendar.SECOND] = prayer.second

            val customTime = calendar.timeInMillis

            if (customTime > currentTime) {
                val delay = customTime - currentTime

                val work = OneTimeWorkRequest
                    .Builder(NotificationWorker::class.java)
                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                    .setInputData(data)
                    .build()

                val workManager = WorkManager.getInstance(context)
                workManager.enqueue(work)
            } else {
                Log.d(TAG, "scheduleNotification: current is greater than custom")
            }
        }
    }

    companion object {
        const val PRAYER_NUM = "prayer_num"
    }
}