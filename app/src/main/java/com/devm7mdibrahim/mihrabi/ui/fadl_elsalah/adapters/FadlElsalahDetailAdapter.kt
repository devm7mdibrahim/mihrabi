package com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FadlDetailItemBinding
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.Fadl
import com.devm7mdibrahim.mihrabi.utils.Constants.TAG

class FadlElsalahDetailAdapter : RecyclerView.Adapter<FadlElsalahDetailAdapter.FadlDetailHolder>() {

    private var fadlList: List<Fadl> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FadlDetailHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<FadlDetailItemBinding>(
                inflater,
                R.layout.fadl_detail_item,
                parent,
                false
            )

        return FadlDetailHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (fadlList.isNotEmpty()) fadlList.size else 0
    }

    override fun onBindViewHolder(holder: FadlDetailHolder, position: Int) {
        val fadl = fadlList[position]
        holder.binding.apply {
            this.fadl = fadl
            executePendingBindings()
        }
    }

    fun submitList(fadlList: List<Fadl>) {
        this.fadlList = fadlList
        notifyDataSetChanged()
    }

    inner class FadlDetailHolder(val binding: FadlDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}