package com.beone.bestpractice.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beone.bestpractice.R;
import com.beone.bestpractice.local.model.CountryEntity;

import java.util.ArrayList;
import java.util.List;

public class CountryMultipleTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final static int TYPE_HEADER = 1;
  private final static int TYPE_COUNTRY = 2;

  private List<CountryEntity> countryEntities = new ArrayList<>();
  private Context context;
  private OnClickItemListener onClickItemListener;
  private String title;

  public CountryMultipleTypeAdapter(String header, Context context, OnClickItemListener onClickItemListener) {
    this.context = context;
    this.onClickItemListener = onClickItemListener;
    this.title = header;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view;
    switch (viewType) {
      case TYPE_HEADER:
        view = LayoutInflater.from(context).inflate(R.layout.item_titleheader, parent, false);
        return new HeaderViewHolder(view);
      case TYPE_COUNTRY:
        view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view, onClickItemListener);
      default:
        throw new IllegalStateException("Unexpected value: " + viewType);
    }
  }


  //Set Position Header or Anything Else
  @Override
  public int getItemViewType(int position) {
    if (isPositionHeader(position)) {
      return TYPE_HEADER;
    } else  {
      return TYPE_COUNTRY;
    }
  }

  private boolean isPositionHeader(int position){
    //true if position 0
    return position == 0;
  }


  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position) == TYPE_HEADER) {
      ((HeaderViewHolder) holder).onbind(title);
    } else if (getItemViewType(position) == TYPE_COUNTRY) {
      //Note : Position - 1 because header outside a list
      ((CountryViewHolder) holder).onbind(countryEntities.get(position-1));
    } else {
      throw new IllegalArgumentException("Undefined View Type");
    }
  }

  public void updatedata(List<CountryEntity> list) {
    countryEntities = list;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    if (!countryEntities.isEmpty() && !TextUtils.isEmpty(title)) {
      return countryEntities.size() + 1;
    } else if (countryEntities.isEmpty() && !TextUtils.isEmpty(title)) {
      return 1;
    } else {
      return 0;
    }
  }

}
