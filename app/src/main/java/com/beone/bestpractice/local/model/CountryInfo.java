package com.beone.bestpractice.local.model;

import androidx.annotation.Nullable;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryInfo {
  @Expose
  @SerializedName("flag")
  private String flag;
  @Expose
  @SerializedName("long")
  private String longtitud;
  @Expose
  @SerializedName("lat")
  private String lat;

  @Expose
  @SerializedName("_id")
  @Ignore
  private int id;

  public String getFlag() {
    return flag;
  }

  public String getLongtitud() {
    return longtitud;
  }

  public String getLat() {
    return lat;
  }


  public int getId() {
    return id;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public void setLongtitud(String longtitud) {
    this.longtitud = longtitud;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }


  public void setId(int id) {
    this.id = id;
  }
}
