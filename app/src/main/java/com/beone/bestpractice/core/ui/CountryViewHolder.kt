package com.beone.bestpractice.core.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.beone.bestpractice.R
import com.beone.bestpractice.core.domain.model.Country
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class CountryViewHolder(itemView: View, onClickItemListener: OnClickItemListener, context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val imgview: ImageView
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
    override fun onClick(view: View) {
        onClickItemListener.onClick(adapterPosition)
    }

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
                .load(countryEntity.countryInfo.flag.toString())
                .format(DecodeFormat.PREFER_RGB_565)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .override(imgview.width, imgview.height)
                .placeholder(R.drawable.pic_placeholder)
//                .listener(object : RequestListener<Bitmap?> {
//                    @SuppressLint("ResourceAsColor")
//                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap?>, isFirstResource: Boolean): Boolean {
//                        cardView.setCardBackgroundColor(R.color.colorWhite)
//                        return false
//                    }
//
//                    @SuppressLint("ResourceAsColor")
//                    override fun onResourceReady(resource: Bitmap?, model: Any, target: Target<Bitmap?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
//                        if (resource != null) {
//                            // Use generated instance
//                            val p = Palette.from(resource).generate()
//                            cardView.setCardBackgroundColor(p.getDominantColor(R.color.colorWhite))
//                        }
//                        return false
//                    }
//                })
                .into(imgview)
    }

    init {
        imgview = itemView.findViewById(R.id.itemImageView)
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