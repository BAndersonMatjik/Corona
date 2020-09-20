package com.example.core.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.core.R
import com.example.core.domain.model.Country

class CountryViewHolder(itemView: View, onClickItemListener: OnClickItemListener, context: Context) : RecyclerView.ViewHolder(itemView) {
    private val imgview: ImageView = itemView.findViewById(R.id.itemImageView)
    private val tvCountry: TextView
    private val tvDeath: TextView
    private val tvDeatsToday: TextView
    private val tvCase: TextView
    private val tvRecoverd: TextView
    private val tvTests: TextView
    private val cardView: CardView
    private val tvCaseToday: TextView

    //datas
    private val context: Context
    private val onClickItemListener: OnClickItemListener

    fun onbind(countryEntity: Country) {
        tvCountry.text = countryEntity.country
        tvCase.text = countryEntity.cases.toString()
        tvCaseToday.text = "+" + countryEntity.todayCases.toString()
        tvDeath.text = countryEntity.deaths.toString()
        tvDeatsToday.text = "+" + countryEntity.todayDeaths.toString()
        tvTests.text = countryEntity.tests.toString()
        tvRecoverd.text = countryEntity.recovered.toString()
        Glide.with(context)
                .asBitmap()
                .load(countryEntity.countryInfo.flag)
                .format(DecodeFormat.PREFER_RGB_565)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .override(imgview.width, imgview.height)
                .placeholder(R.drawable.pic_placeholder)
                .into(imgview)
        itemView.setOnClickListener {
            onClickItemListener.onClick(countryEntity)
        }
    }

    init {
        tvCountry = itemView.findViewById(R.id.itemTvView)
        cardView = itemView.findViewById(R.id.cardview)
        tvDeath = itemView.findViewById(R.id.tvDeaths)
        tvDeatsToday = itemView.findViewById(R.id.tvDeathsToday)
        tvCase = itemView.findViewById(R.id.tvCases)
        tvCaseToday = itemView.findViewById(R.id.tvTodayCases)
        tvTests = itemView.findViewById(R.id.tvTests)
        tvRecoverd = itemView.findViewById(R.id.tvRecovered)
        this.context = context
        this.onClickItemListener = onClickItemListener
    }

}