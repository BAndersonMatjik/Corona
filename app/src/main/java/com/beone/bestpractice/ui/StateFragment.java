package com.beone.bestpractice.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beone.bestpractice.R;
import com.beone.bestpractice.adapter.MainAdapter;
import com.beone.bestpractice.adapter.OnClickItemListener;
import com.beone.bestpractice.local.model.StateEntity;
import com.beone.bestpractice.utility.Resource;

import java.util.List;

import static com.beone.bestpractice.ui.StateViewModel.QUERY_EXHAUSTED;

public class StateFragment extends Fragment implements OnClickItemListener {

  private StateViewModel mViewModel;
  private final static String TAG = StateFragment.class.getName();

  public static StateFragment newInstance() {
    return new StateFragment();
  }

  private RecyclerView recyclerView;
  private MainAdapter mainAdapter;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.state_fragment, container, false);
    recyclerView = view.findViewById(R.id.rcv);

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(StateViewModel.class);
    initRecyclerView();
    subscribeObservers();


  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);


  }

  private void initRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    mainAdapter = new MainAdapter(getContext(), null, this);
    recyclerView.setAdapter(mainAdapter);
  }


  private void subscribeObservers() {
    mViewModel.getStates().observe(getViewLifecycleOwner(), new Observer<Resource<List<StateEntity>>>() {
      @Override
      public void onChanged(Resource<List<StateEntity>> listResource) {
        if (listResource != null) {
          Log.d(TAG, "onChanged: status: " + listResource.status);
          if (listResource.data != null) {
            switch (listResource.status) {
              case ERROR:
                Log.e(TAG, "onChanged: cannot refresh the cache.");
                Log.e(TAG, "onChanged: ERROR message: " + listResource.message);
                Log.e(TAG, "onChanged: status: ERROR, #state: " + listResource.data.size());
                Toast.makeText(getActivity(), listResource.message, Toast.LENGTH_SHORT).show();
                if (listResource.message.equals(QUERY_EXHAUSTED)) {
                  //mainAdapter.setQueryExhausted();
                }
              case SUCCESS:
                Log.i(TAG, "onChanged: cache has been refreshed.");
                Log.i(TAG, "onChanged: status: SUCCESS, #State: " + listResource.data.size());
                mainAdapter.updatedata(listResource.data);
                break;
              case LOADING:
                Log.i(TAG, "onChanged: Loading Data......");
                Toast.makeText(getActivity(), "Loading Data ....", Toast.LENGTH_SHORT).show();
                break;
            }
          }
        }
      }
    });
  }

  @Override
  public <T> void onClick(T data) {
    Log.d(TAG, "onClick: " + data);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    //mViewModel.cancel();
  }
}
