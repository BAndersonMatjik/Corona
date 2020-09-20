package com.example.core.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.tvTitle)
    fun onbind(data: String?) {
        textView.text = data
    }

}