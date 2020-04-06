package com.beone.bestpractice.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.beone.bestpractice.local.model.CountryEntity;
import com.beone.bestpractice.local.model.StateEntity;

@Database(entities = {StateEntity.class, CountryEntity.class}, exportSchema = false, version = 11)
public abstract class CoronaDatabase extends RoomDatabase {
  private static volatile CoronaDatabase INSTANCE;

  public abstract StateDao getStateDao();

  public abstract CountryDao getCountryDao();

  public static CoronaDatabase getInstance(Context context) {
    if (INSTANCE == null) {
      synchronized (CoronaDatabase.class) {
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                CoronaDatabase.class,
                "Corona.db")
                .build();
      }
    }
    return INSTANCE;
  }
}
