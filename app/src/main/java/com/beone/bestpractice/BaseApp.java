package com.beone.bestpractice;

import android.app.Application;
import android.os.StrictMode;

import leakcanary.LeakCanary;

public class BaseApp extends Application {
  public static BaseApp instance;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
  }


  public void enableStrictMode() {
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .penaltyDeath()
            .build());
  }
}
