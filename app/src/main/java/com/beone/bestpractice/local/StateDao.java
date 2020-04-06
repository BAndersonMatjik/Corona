package com.beone.bestpractice.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.beone.bestpractice.local.model.StateEntity;

import java.util.List;

@Dao
public interface StateDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insertStates(StateEntity... stateEntities);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insertState(StateEntity stateEntity);

  @Query("SELECT * FROM statestabel")
  public LiveData<List<StateEntity>> getStates();

}
