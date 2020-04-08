package com.beone.bestpractice.local;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SPreference {
  private final String LAST_FETCHDATA =  "lastfetchdata";

  private SharedPreferences sharedPreferences;

  @SuppressLint("StaticFieldLeak")
  private static SPreference spreference =  null;

  public static SPreference getInstance(Context context){
    if(spreference == null){
      synchronized (SPreference.class){
        spreference = new SPreference(context);
      }
    }
    return spreference;
  }

  private SPreference(Context context) {
    sharedPreferences = context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
  }

  public void setLAST_FETCHDATA(int timestamp){
    sharedPreferences.edit().putInt(LAST_FETCHDATA,timestamp).apply();
  }

  public int getLAST_FETCHDATA(){
    return sharedPreferences.getInt(LAST_FETCHDATA,0);
  }


}
