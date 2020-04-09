package com.beone.bestpractice.ui;

import androidx.fragment.app.testing.FragmentScenario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountryFragmentTest {

  FragmentScenario fragmentScenario;
  @Before
  public void setUp() throws Exception {

  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testCountryFragment() throws Exception{
    fragmentScenario = FragmentScenario.launchInContainer(CountryFragment.newInstance().getClass());
    Thread.sleep(2000);
  }
}