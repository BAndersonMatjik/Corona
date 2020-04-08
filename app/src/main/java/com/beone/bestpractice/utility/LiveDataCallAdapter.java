package com.beone.bestpractice.utility;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

// create by mitchabian : https://github.com/mitchtabian/Local-db-Cache-Retrofit-REST-API-MVVM/blob/master/app/src/main/java/com/codingwithmitch/foodrecipes/util/NetworkBoundResource.java
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<R>> {
  private Type responseType;

  public LiveDataCallAdapter(Type responseType) {
    this.responseType = responseType;
  }

  @Override
  public Type responseType() {
    return responseType;
  }

  @Override
  public LiveData<R> adapt(final Call<R> call) {
    return new LiveData<R>() {
      @Override
      protected void onActive() {
        super.onActive();
        final ApiResponse apiResponse = new ApiResponse();
        if(!call.isExecuted()){
          call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
              postValue((R) apiResponse.create(response));
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
              postValue((R) apiResponse.create(t));
              Log.e("LIVEDATACALLADAPTER", "onFailure: "+call.toString(), t);
            }
          });
        }
      }
    };
  }
}
