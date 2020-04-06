package com.beone.bestpractice.local.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "statestabel")
public class StateEntity {

  @Expose
  @SerializedName("active")
  private int active;
  @Expose
  @SerializedName("todayDeaths")
  private int todayDeaths;
  @Expose
  @SerializedName("deaths")
  private int deaths;
  @Expose
  @SerializedName("todayCases")
  private int todayCases;
  @Expose
  @SerializedName("cases")
  private int cases;

  @Expose
  @PrimaryKey(autoGenerate = false)
  @NonNull
  @SerializedName("state")
  private String state;

  public int getActive() {
    return active;
  }

  public int getTodayDeaths() {
    return todayDeaths;
  }

  public int getDeaths() {
    return deaths;
  }

  public int getTodayCases() {
    return todayCases;
  }

  public int getCases() {
    return cases;
  }

  public String getState() {
    return state;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public void setTodayDeaths(int todayDeaths) {
    this.todayDeaths = todayDeaths;
  }

  public void setDeaths(int deaths) {
    this.deaths = deaths;
  }

  public void setTodayCases(int todayCases) {
    this.todayCases = todayCases;
  }

  public void setCases(int cases) {
    this.cases = cases;
  }

  public void setState(String state) {
    this.state = state;
  }
}
