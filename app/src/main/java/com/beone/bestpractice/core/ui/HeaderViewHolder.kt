package com.beone.bestpractice.core.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beone.bestpractice.R

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textView: TextView
    fun onbind(data: String?) {
        textView.text = data
    }

    init {
        textView = itemView.findViewById(R.id.tvTitle)
    }
}