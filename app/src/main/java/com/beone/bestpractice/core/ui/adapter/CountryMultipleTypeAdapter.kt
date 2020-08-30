package com.beone.bestpractice.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beone.bestpractice.R
import com.beone.bestpractice.core.data.local.entities.CountryEntity
import com.beone.bestpractice.local.model.CountryEntity
import java.util.*

class CountryMultipleTypeAdapter(private val title: String, private val context: Context, private val onClickItemListener: OnClickItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var countryEntities: List<CountryEntity>? = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            TYPE_HEADER -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_titleheader, parent, false)
                HeaderViewHolder(view)
            }
            TYPE_COUNTRY -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false)
                CountryViewHolder(view, onClickItemListener, context)
            }
            else -> throw IllegalStateException("Unexpected value: $viewType")
        }
    }

    //Set Position Header or Anything Else
    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) {
            TYPE_HEADER
        } else {
            TYPE_COUNTRY
        }
    }

    private fun isPositionHeader(position: Int): Boolean {
        //true if position 0
        return position == 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_HEADER) {
            (holder as HeaderViewHolder).onbind(title)
        } else if (getItemViewType(position) == TYPE_COUNTRY) {
            //Note : Position - 1 because header outside a list
            (holder as CountryViewHolder).onbind(countryEntities!![position - 1])
        } else {
            throw IllegalArgumentException("Undefined View Type")
        }
    }

    fun updatedata(list: List<CountryEntity>?) {
        countryEntities = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (!countryEntities!!.isEmpty() && !TextUtils.isEmpty(title)) {
            countryEntities!!.size + 1
        } else if (countryEntities!!.isEmpty() && !TextUtils.isEmpty(title)) {
            1
        } else {
            0
        }
    }

    companion object {
        private const val TYPE_HEADER = 1
        private const val TYPE_COUNTRY = 2
    }

}