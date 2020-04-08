package com.beone.bestpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.beone.bestpractice.ui.CountryFragment;
import com.beone.bestpractice.ui.StateFragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(R.id.framelayout, CountryFragment.newInstance(), "state")
              .commit();
    }
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
  }
}
