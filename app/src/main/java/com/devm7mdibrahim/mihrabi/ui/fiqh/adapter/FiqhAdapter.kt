package com.devm7mdibrahim.mihrabi.ui.fiqh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devm7mdibrahim.mihrabi.R
import com.devm7mdibrahim.mihrabi.model.local.fiqh.Fiqh

class FiqhAdapter : RecyclerView.Adapter<FiqhAdapter.FiqhHolder>() {

    private var fiqhList: List<Fiqh> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiqhHolder {
        return  FiqhHolder(LayoutInflater.from(parent.context).inflate(R.layout.fiqh_item, parent, false))
    }

    override fun getItemCount(): Int {
        return if (!fiqhList.isNullOrEmpty()) fiqhList.size else 0

    }

    fun submitFiqhList(fiqhList: List<Fiqh>){
        this.fiqhList = fiqhList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FiqhHolder, position: Int) {
        val fiqh = fiqhList[position]
        holder.fiqhQuestion.text = fiqh.question
        holder.fiqhAnswer.text = fiqh.answer
    }

    inner class FiqhHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fiqhQuestion : TextView = itemView.findViewById(R.id.fiqh_question_tv)
        val fiqhAnswer : TextView = itemView.findViewById(R.id.fiqh_answer_tv)
    }
}