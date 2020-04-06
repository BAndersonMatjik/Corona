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

  public static CountryRepository getInstance(Context context){
    if(instance==null){
      instance=new CountryRepository(context);
    }
    return instance;
  }

  private CountryRepository(Context context) {
    countryDao = CoronaDatabase.getInstance(context).getCountryDao();
  }

  public LiveData<Resource<List<CountryEntity>>> getCountry(){
    return new NetworkBoundResource<List<CountryEntity>, List<CountryEntity>>(AppExecutors.getInstance()) {
      @Override
      protected void saveCallResult(@NonNull List<CountryEntity> item) {
        if (item != null){
          for (int i = 0; i < item.size(); i++) {
              countryDao.insertCountry(item.get(i));
          }
        }
      }

      @Override
      protected boolean shouldFetch(@Nullable List<CountryEntity> data) {
        return true;
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
