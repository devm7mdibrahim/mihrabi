package com.devm7mdibrahim.mihrabi.ui.quran.surahList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.model.local.surah.Surah
import com.devm7mdibrahim.mihrabi.utils.ItemClickListener
import com.devm7mdibrahim.mihrabi.utils.NumberHelper

class SurahListAdapter(val listener: ItemClickListener) : RecyclerView.Adapter<SurahListAdapter.SurahListHolder>() {

    private var surahList: List<Surah> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahListHolder {
        return SurahListHolder(LayoutInflater.from(parent.context).inflate(R.layout.surah_item, parent, false))
    }

    override fun onBindViewHolder(holder: SurahListHolder, position: Int) {
        val surah = surahList[position]
        holder.surahName.text = surah.surahName
        holder.surahNumber.text = NumberHelper.getArabicNumber(surah.page)
        holder.surahRevelationType.text = surah.revelationType
    }

    override fun getItemCount(): Int {
        return surahList.size
    }

    fun submitList(surahList: List<Surah>) {
        this.surahList = surahList
        notifyDataSetChanged()
    }

    inner class SurahListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val surahNumber: TextView = itemView.findViewById(R.id.surah_number)
        val surahName: TextView = itemView.findViewById(R.id.surah_name)
        val surahRevelationType: TextView = itemView.findViewById(R.id.surah_revelation_type)

        init {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }
}