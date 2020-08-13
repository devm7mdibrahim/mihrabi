package com.devm7mdibrahim.mihrabi.ui.prayer_times.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azan.Time
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.utils.NumberHelper

class PrayersAdapter : RecyclerView.Adapter<PrayersAdapter.PrayersHolder>() {

    private var timesList: Array<Time> = emptyArray()
    private val prayersList = listOf("الفجر", "الشروق", "الظهر", "العصر", "المغرب", "العشاء")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayersHolder {
        return PrayersHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.prayer_item, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PrayersHolder, position: Int) {
        val prayerName = prayersList[position]
        val prayerTime = timesList[position]

        holder.prayerName.text = prayerName

        val displayedPrayerTime: String
        displayedPrayerTime = if (prayerTime.hour >= 12) {
            NumberHelper.getTimeArabicNumber(prayerTime.hour - 12) + ":" + NumberHelper.getTimeArabicNumber(
                prayerTime.minute
            ) + " م"
        } else {
            NumberHelper.getTimeArabicNumber(prayerTime.hour) + ":" + NumberHelper.getTimeArabicNumber(
                prayerTime.minute
            ) + " ص"
        }
        holder.prayerTime.text = displayedPrayerTime
    }

    override fun getItemCount(): Int {
        return timesList.size
    }

    fun setPrayers(times: Array<Time>) {
        timesList = times
    }

    class PrayersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prayerName: TextView = itemView.findViewById(R.id.prayer_name)
        val prayerTime: TextView = itemView.findViewById(R.id.prayer_time)
    }
}