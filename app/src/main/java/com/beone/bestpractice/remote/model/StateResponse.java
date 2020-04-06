package com.beone.bestpractice.remote.model;

import com.beone.bestpractice.local.model.StateEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateResponse {
  @Expose
  private List<StateEntity> stateslist;

  public List<StateEntity> getStateslist() {
    return stateslist;
  }

  public void setStateslist(List<StateEntity> stateslist) {
    this.stateslist = stateslist;
  }
}
