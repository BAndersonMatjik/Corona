package com.beone.bestpractice.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beone.bestpractice.R
import com.beone.bestpractice.adapter.MainAdapter
import com.beone.bestpractice.local.model.StateEntity
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private var arrayList: ArrayList<StateEntity>? = ArrayList()
    private var onClickItemListener: OnClickItemListener
    private var context: Context? = null

    constructor(arrayList: ArrayList<StateEntity>?, onClickItemListener: OnClickItemListener) {
        this.arrayList = arrayList
        this.onClickItemListener = onClickItemListener
    }

    constructor(context: Context?, arrayList: ArrayList<StateEntity>?, onClickItemListener: OnClickItemListener) {
        this.arrayList = arrayList
        this.onClickItemListener = onClickItemListener
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_state, parent, false)
        return ViewHolder(view, onClickItemListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stateEntity = arrayList!![position]
        holder.tvState.text = stateEntity.state
        holder.tvCase.text = stateEntity.cases.toString()
        if (stateEntity.todayCases > 0) {
            holder.tvCaseToday.text = "+" + stateEntity.todayCases.toString()
        } else if (stateEntity.todayCases == 0) {
            holder.tvCaseToday.text = "=" + stateEntity.todayCases.toString()
        }
        holder.tvDeath.text = stateEntity.deaths.toString()
        if (stateEntity.todayDeaths > 0) {
            holder.tvDeatsToday.text = "+" + stateEntity.todayDeaths.toString()
        } else if (stateEntity.todayDeaths == 0) {
            holder.tvDeatsToday.text = "=" + stateEntity.todayDeaths.toString()
        }
    }

    override fun getItemCount(): Int {
        return if (arrayList != null) {
            arrayList!!.size
        } else {
            0
        }
    }

    fun updatedata(l: List<StateEntity>?) {
        arrayList = l as ArrayList<StateEntity>?
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View, var onClickItemListener: OnClickItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var tvState: TextView
        var tvDeath: TextView
        var tvDeatsToday: TextView
        var tvCase: TextView
        var tvCaseToday: TextView
        override fun onClick(view: View) {
            onClickItemListener.onClick(adapterPosition)
        }

        init {
            tvState = itemView.findViewById(R.id.tvState)
            tvCase = itemView.findViewById(R.id.tvCase)
            tvCaseToday = itemView.findViewById(R.id.tvCaseToday)
            tvDeath = itemView.findViewById(R.id.tvDeaths)
            tvDeatsToday = itemView.findViewById(R.id.tvDeathsToday)
            itemView.setOnClickListener(this)
        }
    } //   TODO: 31/03/20 Refactor Name

    companion object {
        private val TAG = MainAdapter::class.java.simpleName
    }
}