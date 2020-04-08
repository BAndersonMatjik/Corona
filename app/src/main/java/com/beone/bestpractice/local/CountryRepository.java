package com.beone.bestpractice.local;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.beone.bestpractice.RetrofitBuilder;
import com.beone.bestpractice.local.model.CountryEntity;
import com.beone.bestpractice.remote.AppExecutors;
import com.beone.bestpractice.utility.ApiResponse;
import com.beone.bestpractice.utility.NetworkBoundResource;
import com.beone.bestpractice.utility.Resource;

import java.util.List;

public class CountryRepository {
  private static final String TAG = CountryRepository.class.getName();

  private static CountryRepository instance;
  private CountryDao countryDao;
  private SPreference sPreference;

  public static CountryRepository getInstance(Context context) {
    if (instance == null) {
      instance = new CountryRepository(context);
    }
    return instance;
  }

  private CountryRepository(Context context) {
    countryDao = CoronaDatabase.getInstance(context).getCountryDao();
    sPreference = SPreference.getInstance(context);
  }

  public LiveData<Resource<List<CountryEntity>>> getCountry() {
    return new NetworkBoundResource<List<CountryEntity>, List<CountryEntity>>(AppExecutors.getInstance()) {
      @Override
      protected void saveCallResult(@NonNull List<CountryEntity> item) {
        if (item != null) {
          for (int i = 0; i < item.size(); i++) {
            countryDao.insertCountry(item.get(i));
          }
          sPreference.setLAST_FETCHDATA((int) (System.currentTimeMillis() / 1000));
        }
      }

      @Override
      protected boolean shouldFetch(@Nullable List<CountryEntity> data) {
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        int lastRefresh = sPreference.getLAST_FETCHDATA();

        Log.d(TAG, "shouldFetch: current time: " + currentTime);
        Log.d(TAG, "shouldFetch: last refresh: " + lastRefresh);
        Log.d(TAG, "shouldFetch: it's been " + ((currentTime - lastRefresh) / 60 / 60 / 24) +
                " days since this was refreshed. 1 days must elapse before refreshing. ");

        //1 day 86400 : 60*60*24*1  second * Minutes * hours * days
        if((currentTime-lastRefresh)>=86400){
          Log.d(TAG, "shouldFetch: SHOULD REFRESH " + true);
          return true;
        }
        Log.d(TAG, "shouldFetch: SHOULD REFRESH " + false);
        return false;
      }

      @NonNull
      @Override
      protected LiveData<List<CountryEntity>> loadFromDb() {

        return countryDao.getCountrys();
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<List<CountryEntity>>> createCall() {
        return RetrofitBuilder
                .getApiService()
                .getCountry();
      }
    }.getAsLiveData();
  }
}
