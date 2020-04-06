package com.beone.bestpractice.local.model;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "countryentity")
public class CountryEntity {

  @Expose
  @SerializedName("critical")
  private int critical;
  @Expose
  @SerializedName("active")
  private int active;
  @Expose
  @SerializedName("recovered")
  private int recovered;
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
  @SerializedName("tests")
  private int tests;



  @Expose
  @SerializedName("countryInfo")
  @Embedded
  private CountryInfo countryInfo;
  @Expose
  @SerializedName("country")
  @PrimaryKey(autoGenerate = false)
  @NonNull
  private String country;

  public int getCritical() {
    return critical;
  }

  public int getActive() {
    return active;
  }

  public int getRecovered() {
    return recovered;
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

  public CountryInfo getCountryInfo() {
    return countryInfo;
  }

  public String getCountry() {
    return country;
  }

  public int getTests() {
    return tests;
  }

  public void setTests(int tests) {
    this.tests = tests;
  }

  public void setCritical(int critical) {
    this.critical = critical;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public void setRecovered(int recovered) {
    this.recovered = recovered;
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

  public void setCountryInfo(CountryInfo countryInfo) {
    this.countryInfo = countryInfo;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
