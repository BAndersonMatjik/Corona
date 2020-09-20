package com.example.favoritefeature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.core.domain.model.Country
import com.example.favoritefeature.di.detailModule
import kotlinx.android.synthetic.main.detailactivity.*
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

class DetailActivity : AppCompatActivity() {
    private var country: Country? = null
    private val viewModel: DetailViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailactivity)

        loadKoinModules(detailModule)

        country = intent.getParcelableExtra("COUNTRYBUNDLE")
        if(country!=null){
            country?.country.let { textView_Country.text = it }
            country?.todayCases?.let { textView_today_case_update.text = "+$it" }
            country?.cases?.let { textView_case.text = it.toString() }
            tv_detailCountry.text = "Lat : "+country?.countryInfo?.lat+"  Long : "+country?.countryInfo?.long
            checkIsFavorite()

            Glide.with(this)
                    .asBitmap()
                    .load(country?.countryInfo?.flag)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(com.example.core.R.drawable.pic_placeholder)
                    .into(imgView_flag)
            cardview_favorite.setOnClickListener {
                country?.country?.let {it1 ->
                    if (country?.isFavorite!!) {
                        viewModel.updateFavoriteCountry(it1, false)
                        country?.isFavorite = false
                    } else if(!country?.isFavorite!!) {
                        viewModel.updateFavoriteCountry(it1,true)
                        country?.isFavorite = true
                    }
                }
                checkIsFavorite()
            }
        }
    }


    private fun checkIsFavorite(){
        country?.isFavorite?.let {
            if(it){
                tv_favorite.text = "Delete From favorite"
            }else{
                tv_favorite.text = "Add favorite"
            }
        }
    }
}