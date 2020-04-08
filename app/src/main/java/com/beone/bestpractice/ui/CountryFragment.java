package com.beone.bestpractice.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beone.bestpractice.R;
import com.beone.bestpractice.adapter.CountryMultipleTypeAdapter;
import com.beone.bestpractice.adapter.OnClickItemListener;
import com.beone.bestpractice.local.model.CountryEntity;
import com.beone.bestpractice.utility.Resource;

import java.util.List;

public class CountryFragment extends Fragment implements OnClickItemListener {

  private static final String TAG = CountryFragment.class.getName();
  private CountryViewModel mViewModel;

  public static CountryFragment newInstance() {
    return new CountryFragment();
  }

  private RecyclerView recyclerView;
  private CountryMultipleTypeAdapter countryMultipleTypeAdapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.country_fragment, container, false);
    recyclerView = v.findViewById(R.id.recyclerview);

    initrecycler();

    return v;
  }

  private void initrecycler(){
    countryMultipleTypeAdapter = new CountryMultipleTypeAdapter("Covid-19",requireContext(),this);
    recyclerView.setItemViewCacheSize(20);
    recyclerView.setHasFixedSize(true);
    recyclerView.setDrawingCacheEnabled(true);
    recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
    recyclerView.setAdapter(countryMultipleTypeAdapter);
  }

  private void subscribeviewmodel(){
    mViewModel.getCountrys().observe(getViewLifecycleOwner(), new Observer<Resource<List<CountryEntity>>>() {
      @Override
      public void onChanged(Resource<List<CountryEntity>> listResource) {
        if (listResource.data != null) {
          switch (listResource.status) {
            case ERROR:
              Log.e(TAG, "onChanged: cannot refresh the cache.");
              Log.e(TAG, "onChanged: ERROR message: " + listResource.message);
              Log.e(TAG, "onChanged: status: ERROR, #state: " + listResource.data.size());
              Toast.makeText(getContext(), listResource.message, Toast.LENGTH_SHORT).show();
            case SUCCESS:
              Log.i(TAG, "onChanged: Success Data Get");
              countryMultipleTypeAdapter.updatedata(listResource.data);
              break;
            case LOADING:
              Log.i(TAG, "onChanged: Loading");
              Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();
              break;
          }
        }
      }
    });
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
    subscribeviewmodel();
  }


  @Override
  public <T> void onClick(T data) {
      mViewModel.test();
  }
}
