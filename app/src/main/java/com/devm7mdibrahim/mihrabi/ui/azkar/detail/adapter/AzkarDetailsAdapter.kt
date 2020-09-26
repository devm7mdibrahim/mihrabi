package com.devm7mdibrahim.mihrabi.ui.azkar.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.ZekrItemBinding
import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar

class AzkarDetailsAdapter : RecyclerView.Adapter<AzkarDetailsAdapter.AzkarHolder>() {

    private var azkarList: List<Azkar> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AzkarHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ZekrItemBinding>(inflater, R.layout.zekr_item, parent, false)
        return AzkarHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AzkarHolder, position: Int) {
        val azkar = azkarList[position]
        holder.binding.apply {
            zekr = azkar
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return if (!azkarList.isNullOrEmpty()) azkarList.size else 0
    }

    fun submitList(azkarList: List<Azkar>) {
        this.azkarList = azkarList
        notifyDataSetChanged()
    }

    inner class AzkarHolder(val binding: ZekrItemBinding) : RecyclerView.ViewHolder(binding.root)
}