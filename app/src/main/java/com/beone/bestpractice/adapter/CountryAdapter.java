package com.beone.bestpractice.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

  private List<CountryEntity> countryEntities = new ArrayList<>();
  private Context context;
  private OnClickItemListener onClickItemListener;

  public CountryAdapter(List<CountryEntity> countryEntities, Context context,OnClickItemListener onClickItemListener) {
    this.countryEntities = countryEntities;
    this.context = context;
    this.onClickItemListener = onClickItemListener;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    CountryEntity countryEntity = countryEntities.get(position);
    holder.tvCountry.setText(countryEntity.getCountry());

    holder.tvCase.setText(String.valueOf(countryEntity.getCases()));
    holder.tvCaseToday.setText("+"+String.valueOf(countryEntity.getTodayCases()));
    holder.tvDeath.setText(String.valueOf(countryEntity.getDeaths()));
    holder.tvDeatsToday.setText("+"+String.valueOf(countryEntity.getTodayDeaths()));
    holder.tvTests.setText(String.valueOf(countryEntity.getTests()));
    holder.tvRecoverd.setText(String.valueOf(countryEntity.getRecovered()));

    Glide.with(context)
            .asBitmap()
            .load(countryEntity.getCountryInfo().getFlag())
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.pic_placeholder)
            .listener(new RequestListener<Bitmap>() {
              @Override
              public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
              }

              @SuppressLint("ResourceAsColor")
              @Override
              public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                if (resource != null) {
                  Palette p = Palette.from(resource).generate();
//                  // Use generated instance
                  holder.cardView.setCardBackgroundColor(p.getDominantColor(R.color.colorWhite));
                }
                return false;
              }
            })
            .into(holder.imgview);

  }

  public void updatedata(List<CountryEntity> list){
    countryEntities = list;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {

    if (countryEntities !=null){
      return countryEntities.size();
    }else {
      return 0;
    }
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imgview;
    private TextView tvCountry,tvDeath, tvDeatsToday, tvCase, tvCaseToday, tvRecoverd, tvTests;;
    private CardView cardView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      imgview = itemView.findViewById(R.id.itemImageView);
      tvCountry = itemView.findViewById(R.id.itemTvView);
      cardView = itemView.findViewById(R.id.cardview);
      tvDeath = itemView.findViewById(R.id.tvDeaths);
      tvDeatsToday = itemView.findViewById(R.id.tvDeathsToday);
      tvCase = itemView.findViewById(R.id.tvCases);
      tvCaseToday = itemView.findViewById(R.id.tvTodayCases);
      tvTests =itemView.findViewById(R.id.tvTests);
      tvRecoverd = itemView.findViewById(R.id.tvRecovered);
    }

    @Override
    public void onClick(View view) {
     onClickItemListener.onClick(getAdapterPosition());
    }
  }
}
