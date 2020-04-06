package com.beone.bestpractice.remote;

import androidx.lifecycle.LiveData;

import com.beone.bestpractice.local.model.CountryEntity;
import com.beone.bestpractice.local.model.StateEntity;
import com.beone.bestpractice.utility.ApiResponse;
import com.beone.bestpractice.remote.model.StateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

  @GET("states")
  LiveData<ApiResponse<StateResponse>> getState();

  @GET("states")
  LiveData<ApiResponse<List<StateEntity>>> getState2();

  @GET("countries")
  LiveData<ApiResponse<List<CountryEntity>>> getCountry();

}
