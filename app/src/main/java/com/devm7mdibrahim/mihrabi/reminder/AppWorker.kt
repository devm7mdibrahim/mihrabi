package com.devm7mdibrahim.mihrabi.reminder

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.*
import com.azan.Azan
import com.azan.Method.Companion.EGYPT_SURVEY
import com.azan.Time
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.devm7mdibrahim.mihrabi.ui.prayer_times.repo.PrayerTimesRepository
import com.devm7mdibrahim.mihrabi.ui.prayer_times.repo.PrayerTimesRepositoryImpl
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG
import java.lang.System.currentTimeMillis
import java.util.*
import java.util.concurrent.TimeUnit

class AppWorker @WorkerInject constructor(@Assisted val context: Context, @Assisted params: WorkerParameters, private val prayerTimesRepository: PrayerTimesRepositoryImpl) : Worker(context, params) {
    override fun doWork(): Result {
        getPrayerTimes()
        return Result.success()
    }

    private fun getPrayerTimes() {
        val latitude = prayerTimesRepository.getUserLatitude()
        val longitude = prayerTimesRepository.getUserLongitude()

        //get prayer times
        val mGMTOffset = GregorianCalendar().timeZone.rawOffset
        val gmtDiff = TimeUnit.HOURS.convert(
            mGMTOffset.toLong(),
            TimeUnit.MILLISECONDS
        )

        val today = SimpleDate(GregorianCalendar())
        val location = Location(
            latitude,
            longitude,
            gmtDiff.toDouble(),
            0
        )
        val azan = Azan(location, EGYPT_SURVEY)

        val prayers = azan.getPrayerTimes(today).times

//        val prayers = arrayOf(
//            Time(22, 40, 0, false),
//            Time(22, 41, 0, false),
//            Time(22, 42, 0, false),
//            Time(22, 43, 0, false),
//            Time(22, 44, 0, false),
//            Time(22, 45, 0, false)
//        )

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