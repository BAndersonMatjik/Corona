package com.beone.bestpractice.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beone.bestpractice.R;
import com.beone.bestpractice.local.model.StateEntity;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

  private static final String TAG = MainAdapter.class.getSimpleName();

  private ArrayList<StateEntity> arrayList = new ArrayList<>();
  private OnClickItemListener onClickItemListener;
  private Context context;

  public MainAdapter(ArrayList<StateEntity> arrayList, OnClickItemListener onClickItemListener) {
    this.arrayList = arrayList;
    this.onClickItemListener = onClickItemListener;
  }

  public MainAdapter(Context context, ArrayList<StateEntity> arrayList, OnClickItemListener onClickItemListener) {
    this.arrayList = arrayList;
    this.onClickItemListener = onClickItemListener;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_state, parent, false);
    return new ViewHolder(view, onClickItemListener);
  }


  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    StateEntity stateEntity = arrayList.get(position);
    holder.tvState.setText(stateEntity.getState());
    holder.tvCase.setText(String.valueOf(stateEntity.getCases()));
    if (stateEntity.getTodayCases() > 0) {
      holder.tvCaseToday.setText("+" + String.valueOf(stateEntity.getTodayCases()));
    } else if (stateEntity.getTodayCases() == 0) {
      holder.tvCaseToday.setText("=" +String.valueOf(stateEntity.getTodayCases()));
    }

    holder.tvDeath.setText(String.valueOf(stateEntity.getDeaths()));
    if (stateEntity.getTodayDeaths() > 0) {
      holder.tvDeatsToday.setText("+" + String.valueOf(stateEntity.getTodayDeaths()));
    } else if (stateEntity.getTodayDeaths()==0){
      holder.tvDeatsToday.setText("=" + String.valueOf(stateEntity.getTodayDeaths()));
    }
  }

  @Override
  public int getItemCount() {
    if (arrayList != null) {
      return arrayList.size();
    } else {
      return 0;
    }
  }

  public void updatedata(List<StateEntity> l) {
    arrayList = (ArrayList<StateEntity>) l;
    notifyDataSetChanged();
  }


  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tvState, tvDeath, tvDeatsToday, tvCase, tvCaseToday;
    OnClickItemListener onClickItemListener;

    public ViewHolder(@NonNull View itemView, OnClickItemListener onClickItemListener) {
      super(itemView);
      this.onClickItemListener = onClickItemListener;

      tvState = itemView.findViewById(R.id.tvState);
      tvCase = itemView.findViewById(R.id.tvCase);
      tvCaseToday = itemView.findViewById(R.id.tvCaseToday);
      tvDeath = itemView.findViewById(R.id.tvDeaths);
      tvDeatsToday = itemView.findViewById(R.id.tvDeathsToday);

      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      onClickItemListener.onClick(getAdapterPosition());
    }
  }
//   TODO: 31/03/20 Refactor Name
}

/*** in Activity or fragment
 private void initRecyclerView(){
 LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
 mRecyclerView.setLayoutManager(linearLayoutManager);
 VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
 mRecyclerView.addItemDecoration(itemDecorator);
 new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
 mNoteRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
 mRecyclerView.setAdapter(mNoteRecyclerAdapter);
 }***/
