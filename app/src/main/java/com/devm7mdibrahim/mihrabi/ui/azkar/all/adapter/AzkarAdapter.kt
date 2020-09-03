package com.devm7mdibrahim.mihrabi.ui.azkar.all.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.AzkarItemBinding
import com.devm7mdibrahim.mihrabi.model.local.azkar.Azkar
import com.devm7mdibrahim.mihrabi.utils.ItemClickListener

class AzkarAdapter constructor(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<AzkarAdapter.AzkarHolder>() {

    private var azkarList: List<Azkar> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AzkarHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<AzkarItemBinding>(inflater, R.layout.azkar_item, parent, false)
        return AzkarHolder(binding)
    }

    override fun onBindViewHolder(holder: AzkarHolder, position: Int) {
        val azkar = azkarList[position]
        holder.bind(azkar)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return if (!azkarList.isNullOrEmpty()) azkarList.size else 0
    }

    fun submitList(azkarList: List<Azkar>) {
        this.azkarList = azkarList
        notifyDataSetChanged()
    }

    inner class AzkarHolder(val binding: AzkarItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(azkar: Azkar) = with(binding) {
            zekr = azkar
        }
    }
}