package com.beone.bestpractice.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.beone.bestpractice.R;
import com.beone.bestpractice.local.model.CountryEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  private ImageView imgview;
  private TextView tvCountry;
  private TextView tvDeath;
  private TextView tvDeatsToday;
  private TextView tvCase;
  private TextView tvRecoverd;
  private TextView tvTests;
  private CardView cardView;
  private TextView tvCaseToday;

  //data
  private Context context;
  private OnClickItemListener onClickItemListener;

  public CountryViewHolder(@NonNull View itemView, OnClickItemListener onClickItemListener,Context context) {
    super(itemView);

    imgview = itemView.findViewById(R.id.itemImageView);
    tvCountry = itemView.findViewById(R.id.itemTvView);
    cardView = itemView.findViewById(R.id.cardview);
    tvDeath = itemView.findViewById(R.id.tvDeaths);
    tvDeatsToday = itemView.findViewById(R.id.tvDeathsToday);
    tvCase = itemView.findViewById(R.id.tvCases);
    tvCaseToday = itemView.findViewById(R.id.tvTodayCases);
    tvTests = itemView.findViewById(R.id.tvTests);
    tvRecoverd = itemView.findViewById(R.id.tvRecovered);

    this.context = context;
    this.onClickItemListener = onClickItemListener;
  }


  @Override
  public void onClick(View view) {
    onClickItemListener.onClick(getAdapterPosition());
  }

  public void onbind(CountryEntity countryEntity) {
    tvCountry.setText(countryEntity.getCountry());
    tvCase.setText(String.valueOf(countryEntity.getCases()));
    tvCaseToday.setText("+" + String.valueOf(countryEntity.getTodayCases()));
    tvDeath.setText(String.valueOf(countryEntity.getDeaths()));
    tvDeatsToday.setText("+" + String.valueOf(countryEntity.getTodayDeaths()));
    tvTests.setText(String.valueOf(countryEntity.getTests()));
    tvRecoverd.setText(String.valueOf(countryEntity.getRecovered()));
    Glide.with(context)
            .asBitmap()
            .load(countryEntity.getCountryInfo().getFlag())
            .format(DecodeFormat.PREFER_RGB_565)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(imgview.getWidth(),imgview.getHeight())
            .placeholder(R.drawable.pic_placeholder)
            .listener(new RequestListener<Bitmap>() {
              @SuppressLint("ResourceAsColor")
              @Override
              public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                cardView.setCardBackgroundColor(R.color.colorWhite);
                return false;
              }

              @SuppressLint("ResourceAsColor")
              @Override
              public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                if (resource != null) {
                  // Use generated instance
                  Palette p = Palette.from(resource).generate();
                  cardView.setCardBackgroundColor(p.getDominantColor(R.color.colorWhite));
                }
                return false;
              }
            })
            .into(imgview);
  }
}
