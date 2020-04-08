package com.beone.bestpractice.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beone.bestpractice.R;

public class HeaderViewHolder extends RecyclerView.ViewHolder{
  private TextView textView;

  public HeaderViewHolder(@NonNull View itemView) {
    super(itemView);
    textView = itemView.findViewById(R.id.tvTitle);
  }

  public void onbind(String data) {
      textView.setText(data);
  }
}
