package com.beone.bestpractice.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.beone.bestpractice.local.CountryRepository;
import com.beone.bestpractice.local.model.CountryEntity;
import com.beone.bestpractice.local.model.StateEntity;
import com.beone.bestpractice.utility.Resource;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {
  private static final String TAG = CountryViewModel.class.getName();
  private MediatorLiveData<Resource<List<CountryEntity>>> country = new MediatorLiveData<>();
  private CountryRepository countryRepository;

  private long requestStartTime;

  public CountryViewModel(@NonNull Application application) {
    super(application);
    countryRepository = CountryRepository.getInstance(application);
  }

  public LiveData<Resource<List<CountryEntity>>> getCountrys() {
    test();
    return country;
  }

  public void test() {
    requestStartTime = System.currentTimeMillis();

    final LiveData<Resource<List<CountryEntity>>> resourceLiveData = countryRepository.getCountry();

    country.addSource(resourceLiveData, new Observer<Resource<List<CountryEntity>>>() {
      @Override
      public void onChanged(Resource<List<CountryEntity>> listResource) {
        if (listResource != null) {
          if (listResource.status == Resource.Status.SUCCESS) {
            Log.d(TAG, "onChanged: REQUEST TIME: " + (System.currentTimeMillis() - requestStartTime) / 1000 + " seconds.");
            Log.d(TAG, "onChanged: " + listResource.data);
            if (listResource.data != null) {
              if (listResource.data.size() == 0) {
                country.setValue(new Resource<>(
                        Resource.Status.ERROR,
                        listResource.data,
                        "No More Result"));
                Log.d(TAG, "onChanged: ERROR  1");
              }
            }
            country.removeSource(resourceLiveData);
          } else if (listResource.status == Resource.Status.ERROR) {
            country.removeSource(resourceLiveData);
            Log.d(TAG, "onChanged: ERROR 2");
          }
          country.setValue(listResource);
        } else {
          country.removeSource(resourceLiveData);
        }
      }
    });
  }

}
