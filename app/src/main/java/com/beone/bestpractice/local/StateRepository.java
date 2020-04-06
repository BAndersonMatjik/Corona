package com.beone.bestpractice.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.beone.bestpractice.remote.AppExecutors;
import com.beone.bestpractice.RetrofitBuilder;
import com.beone.bestpractice.local.model.StateEntity;
import com.beone.bestpractice.utility.ApiResponse;
import com.beone.bestpractice.remote.model.StateResponse;
import com.beone.bestpractice.utility.NetworkBoundResource;
import com.beone.bestpractice.utility.Resource;

import java.util.List;

public class StateRepository {

  private static final String TAG = StateRepository.class.getName();

  private static StateRepository instance;
  private StateDao stateDao;

  public static StateRepository getInstance(Context context) {
    if (instance == null) {
      instance = new StateRepository(context);
    }
    return instance;
  }

  private StateRepository(Context context) {
    stateDao = CoronaDatabase.getInstance(context).getStateDao();
  }

  public LiveData<Resource<List<StateEntity>>> getStateCorona2() {
    return new NetworkBoundResource<List<StateEntity>, StateResponse>(AppExecutors.getInstance()) {

      @Override
      protected void saveCallResult(@NonNull StateResponse item) {
        if (item.getStateslist() != null) { // recipe list will be null if the api key is expired
          //Log.d(TAG, "saveCallResult: states response: " + item.toString());
          for (int i = 0; i < item.getStateslist().size(); i++) {
            stateDao.insertState(item.getStateslist().get(i));
          }
        }

      }

      @Override
      protected boolean shouldFetch(@Nullable List<StateEntity> data) {
        return true;
      }

      @NonNull
      @Override
      protected LiveData<List<StateEntity>> loadFromDb() {
        return stateDao.getStates();
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<StateResponse>> createCall() {
        return RetrofitBuilder
                .getApiService()
                .getState();
      }
    }.getAsLiveData();
  }

  public LiveData<Resource<List<StateEntity>>> getStateCorona() {
    return new NetworkBoundResource<List<StateEntity>, List<StateEntity>>(AppExecutors.getInstance()) {

      @Override
      protected void saveCallResult(@NonNull List<StateEntity> item) {
        if (item != null) { // recipe list will be null if the api key is expired
         // Log.d(TAG, "saveCallResult: states response: " + item.toString());
          for (int i = 0; i < item.size(); i++) {
            stateDao.insertState(item.get(i));
          }
        }
      }

      @Override
      protected boolean shouldFetch(@Nullable List<StateEntity> data) {
        return true;
      }

      @NonNull
      @Override
      protected LiveData<List<StateEntity>> loadFromDb() {
        return stateDao.getStates();
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<List<StateEntity>>> createCall() {
        return RetrofitBuilder
                .getApiService()
                .getState2();
      }
    }.getAsLiveData();
  }


}
