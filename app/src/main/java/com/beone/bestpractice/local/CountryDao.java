package com.beone.bestpractice.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.beone.bestpractice.local.model.CountryEntity;

import java.util.List;

@Dao
public interface CountryDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insertCountrys(CountryEntity... countryEntities);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insertCountry(CountryEntity countryEntity);

  @Query("SELECT * FROM countryentity ORDER BY cases DESC")
  public LiveData<List<CountryEntity>> getCountrys();

}
