package com.devm7mdibrahim.mihrabi.ui.fadl_elsalah.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.databinding.FadlItemBinding
import com.devm7mdibrahim.mihrabi.model.local.fadl_salah.Fadl
import com.devm7mdibrahim.mihrabi.utils.ItemClickListener

class FadlElsalahAdapter constructor(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<FadlElsalahAdapter.FadlHolder>() {

    private var fadlList: List<Fadl> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FadlHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<FadlItemBinding>(inflater, R.layout.fadl_item, parent, false)

        return FadlHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (fadlList.isNotEmpty()) fadlList.size else 0
    }

    override fun onBindViewHolder(holder: FadlHolder, position: Int) {
        val fadl = fadlList[position]
        holder.binding.apply {
            this.fadl = fadl
            executePendingBindings()

            root.setOnClickListener {
                itemClickListener.onItemClick(holder.adapterPosition)
            }
        }
    }

    fun submitList(fadlList: List<Fadl>) {
        this.fadlList = fadlList
        notifyDataSetChanged()
    }

    inner class FadlHolder(val binding: FadlItemBinding) : RecyclerView.ViewHolder(binding.root)
}