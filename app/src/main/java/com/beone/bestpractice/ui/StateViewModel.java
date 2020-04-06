package com.beone.bestpractice.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.beone.bestpractice.local.StateRepository;
import com.beone.bestpractice.local.model.StateEntity;
import com.beone.bestpractice.utility.Resource;

import java.util.List;

public class StateViewModel extends AndroidViewModel {
  private static final String TAG = StateViewModel.class.getName();

  public static final String QUERY_EXHAUSTED = "No More Result";
  private MediatorLiveData<Resource<List<StateEntity>>> states = new MediatorLiveData<>();
  private StateRepository stateRepository;

  public enum ViewState{

  }

  // query extras
  private boolean isQueryExhausted;
  private boolean isPerformingQuery;
  private int pageNumber;
  private String query;
  private boolean cancelRequest;
  private long requestStartTime;

  public StateViewModel(@NonNull Application application) {
    super(application);
    stateRepository = StateRepository.getInstance(application);
  }

  public LiveData<Resource<List<StateEntity>>> getStates(){
    test();
    return states;
  }

  public void test(){
    requestStartTime = System.currentTimeMillis();
    cancelRequest = false;
    isPerformingQuery = true;
    final LiveData<Resource<List<StateEntity>>> repositorySource =  stateRepository.getStateCorona();
    states.addSource(repositorySource, new Observer<Resource<List<StateEntity>>>() {
      @Override
      public void onChanged(Resource<List<StateEntity>> listResource) {
        if(!cancelRequest) {
          if (listResource != null) {
            if (listResource.status == Resource.Status.SUCCESS) {
              Log.d(TAG, "onChanged: REQUEST TIME: " + (System.currentTimeMillis() - requestStartTime) / 1000 + " seconds.");
              Log.d(TAG, "onChanged: " + listResource.data);

              isPerformingQuery = false;

              if (listResource.data != null) {
                if (listResource.data.size() == 0) {
                  Log.d(TAG, "onChanged: query is exhausted...");
                  states.setValue(
                          new Resource<List<StateEntity>>(
                                  Resource.Status.ERROR,
                                  listResource.data,
                                  QUERY_EXHAUSTED
                          )
                  );
                  isQueryExhausted = true;
                }
              }
              states.removeSource(repositorySource);
            } else if (listResource.status == Resource.Status.ERROR) {
              Log.d(TAG, "onChanged: REQUEST TIME: " + (System.currentTimeMillis() - requestStartTime) / 1000 + " seconds.");
              isPerformingQuery = false;
              if (listResource.message.equals(QUERY_EXHAUSTED)) {
                isQueryExhausted = true;
              }
              states.removeSource(repositorySource);
            }
            states.setValue(listResource);
          } else {
            states.removeSource(repositorySource);
          }
        }else {
          states.removeSource(repositorySource);
        }
      }
    });
  }


  public void cancel(){
    if (isPerformingQuery){
      Log.d(TAG, "cancelSearchRequest: canceling the search request.");
      cancelRequest = true;
      isPerformingQuery = false;
      pageNumber = 1;
    }
  }
}
