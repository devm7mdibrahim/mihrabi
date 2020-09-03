package com.devm7mdibrahim.mihrabi.ui.quran.quran.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devm7mdibrahim.mihrabi.databinding.PageItemBinding
import com.devm7mdibrahim.mihrabi.utils.Constants
import com.devm7mdibrahim.mihrabi.utils.NumberHelper
import com.devm7mdibrahim.mihrabi.utils.SpannableHelper

class PageAdapter : RecyclerView.Adapter<PageAdapter.PageHolder>() {
    private var pagelist: List<Page> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PageHolder {
        return PageHolder(
            PageItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val page = pagelist[position]
        holder.bind(page)
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

    inner class PageHolder(val binding: PageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(page: Page) {
            val ayah = page.getText { return@getText getSurahNameFromIndex(it) }

            with(binding) {
                pageAyahsTextView.setText(
                    SpannableHelper.getSpannable(ayah),
                    TextView.BufferType.SPANNABLE
                )

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    pageAyahsTextView.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
                }

                pageNumberTextView.text = NumberHelper.getArabicNumber(page.pageNumber)

                surahNameTextView.text = Constants.SURAH_NAMES[page.ayat[0].surahId - 1]

                juzTextView.text = " الجزء " + NumberHelper.getArabicNumber(page.juz)
            }
        }
    }
}