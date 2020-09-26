package com.devm7mdibrahim.mihrabi.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            val work = PeriodicWorkRequestBuilder<AppWorker>(1, TimeUnit.DAYS).build()
            val workManager = WorkManager.getInstance(context)
            workManager.enqueue(work)
        }
    }
}