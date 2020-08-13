package com.devm7mdibrahim.mihrabi.ui.quran.quran.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.NumberHelper
import com.devm7mdibrahim.mihrabi.utils.SpannableHelper

class PageAdapter : RecyclerView.Adapter<PageAdapter.PageHolder>() {
    private var pagelist: List<Page> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PageHolder {
        return PageHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.page_item, viewGroup, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val page = pagelist[position]

        val ayah = page.getText { return@getText getSurahNameFromIndex(it) }
        holder.pageAyahsTextView.setText(SpannableHelper.getSpannable(ayah), TextView.BufferType.SPANNABLE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.pageAyahsTextView.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }

        holder.pageNumberTextView.text = NumberHelper.getArabicNumber(page.pageNumber)

        val surahName = Constants.SURAH_NAMES[page.ayat[0].surahId -1]
        holder.surahNameTextView.text = surahName

        holder.juzTextView.text = " الجزء " + NumberHelper.getArabicNumber(page.juz)
    }

    override fun getItemCount(): Int {
        return pagelist.size
    }

    fun submitList(pageList: List<Page>) {
        this.pagelist = pageList
        notifyDataSetChanged()
    }

    private fun getSurahNameFromIndex(surahIndex: Int): String {
        return Constants.SURAH_NAMES[surahIndex - 1]
    }

    inner class PageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pageAyahsTextView: TextView = itemView.findViewById(R.id.pageAyahsTextView)
        var surahNameTextView: TextView = itemView.findViewById(R.id.surahNameTextView)
        var juzTextView: TextView = itemView.findViewById(R.id.juzTextView)
        var pageNumberTextView: TextView = itemView.findViewById(R.id.pageNumberTextView)
    }
}